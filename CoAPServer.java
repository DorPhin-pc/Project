package 임베디드;

import java.io.IOException;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.ws4d.coap.messages.CoapMediaType;
import org.ws4d.coap.rest.BasicCoapResource;
import org.ws4d.coap.rest.CoapResourceServer;
import org.ws4d.coap.rest.ResourceHandler;

import com.pi4j.io.gpio.*;
import com.pi4j.io.spi.*;
import com.pi4j.util.CommandArgumentParser;

public class CoAPServer {
	public static final GpioController gpio = GpioFactory.getInstance();
	
	private static CoAPServer sampleServer;
	private CoapResourceServer resourceServer;
	private static Logger logger = Logger.getLogger(CoAPServer.class.getName());
	
	public static SpiDevice spi = null;
	public static String a0 = "1.131786e-003";
	public static String a1 = "2.336422e-004";
	public static String a3 = "8.985024e-008";
	public static String T0 = "273.15";

	private static short ADC_CHANNEL_COUNT = 4;
	
	public static Pin pin =null;
	public static GpioPinPwmOutput pwm =null;
	public static String Target="0";
	public void gpioInit(){
		pin = CommandArgumentParser.getPin(RaspiPin.class, RaspiPin.GPIO_04);
		pwm = gpio.provisionSoftPwmOutputPin(pin);
		pwm.setPwmRange(1023);
		
		try{
			spi = SpiFactory.getInstance(SpiChannel.CS0,SpiDevice.DEFAULT_SPI_SPEED,SpiDevice.DEFAULT_SPI_MODE);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	private void run() throws IOException{
		if(resourceServer != null)
			resourceServer.stop();
		resourceServer = new CoapResourceServer();
		
		Logger resourceLogger = Logger.getLogger(CoapResourceServer.class.getName());
		resourceLogger.setLevel(Level.ALL);
		
		final BasicCoapResource Temp = new BasicCoapResource("/temperature", "".getBytes(), CoapMediaType.text_plain);
		Temp.registerResourceHandler(new ResourceHandler() {
			public void onPost(byte[] data) {
				Target = new String(data);
				System.out.println("Post to /temperature");
			}
		}
		);
		Temp.setObservable(true);
		resourceServer.createResource(Temp);
		try{
			resourceServer.start();
		}catch(Exception e){
			e.printStackTrace();
		}
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try{
				String getTemp = getTemp();
				Temp.setValue(((String)getTemp).getBytes());
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			Temp.changed();
		}
	}
	public String getTemp()throws InterruptedException, IOException{
		pwm.setPwm(500);
		while(true){
			double u = read()/1023.0;
			double R = (1/u-1)*10000;
			double InR = Math.log(R);
			double temp = Double.parseDouble(a0)+Double.parseDouble(a1)*InR
					+Double.parseDouble(a3)*Math.pow(InR, 3);
			double inv = 1/temp;
			double temper = inv - Double.parseDouble(T0);
			if(Double.parseDouble(Target) <= temper)
				pwm.setPwm(0);
			if(Double.parseDouble(Target) >= temper)
				pwm.setPwm(500);
			System.out.println("온도: "+ String.format("%.2f", temper));
			return String.format("%.2f", temper);
		}
	}
	public static int read() throws IOException, InterruptedException {
		for(short channel = 0; channel < ADC_CHANNEL_COUNT; channel++){
			if(channel == 0){
				int conversion_value = getConversionValue(channel);
				//System.out.println("adc value: "+conversion_value);
				Thread.sleep(250);
				return conversion_value;
			}
		}
		Thread.sleep(250);
		return 0;
	}
	public static int getConversionValue(short channel) throws IOException {

		// create a data buffer and initialize a conversion request payload
		byte data[] = new byte[] {
				(byte) 0b00000001,                              
				// first byte, start bit
				(byte)(0b10000000 |( ((channel & 7) << 4))),    
				// second byte transmitted -> (SGL/DIF = 1, D2=D1=D0=0)
				(byte) 0b00000000                               
				// third byte transmitted....don't care
		};

		// send conversion request to ADC chip via SPI channel
		byte[] result = spi.write(data);

		// calculate and return conversion value from result bytes
		int value = (result[1]<< 8) & 0b1100000000; //merge data[1] & data[2] to get 10-bit result
		value |=  (result[2] & 0xff);
		return value;
	}
	
	public static void main(String[] args) throws IOException{
		logger.addAppender(new ConsoleAppender(new SimpleLayout()));
		logger.setLevel(Level.INFO);
		logger.info("Start Induction Server");
		sampleServer = new CoAPServer();
		sampleServer.gpioInit();
		sampleServer.run();
		Target = "0";
	}
	
}
