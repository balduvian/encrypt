package encrypt;

public class Race {
	
	public String plain = "secret message";
	
	public static void main(String[] args) {
		new Race();
	}
	
	public Race(){
		ascii(progressive(false,progressive(true,plain.getBytes(),1),1));
		ascii(shift(false,shift(true,plain.getBytes(),1),1));
		ascii(escalate(false,escalate(true,plain.getBytes(),1),1));
		ascii(progressive(true,plain.getBytes(),1));
		ascii(shift(true,plain.getBytes(),1));
		ascii(escalate(true,plain.getBytes(),1));
		for(int i=0;i<256;i++){
			System.out.println((char)(byte)(i) + " " + (byte)(i));
		}
	}
	
	//start of encrypting methods
	public byte[] shift(boolean enc, byte[] b, int c){
		if(enc){
			for(int i=0;i<b.length;i++){
				b[i] = (byte)((b[i]+c));
			}
			return b;
		}else{
			for(int i=0;i<b.length;i++){
				b[i] = (byte)((b[i]-c));
			}
			return b;
		}
	}
	
	public byte[] escalate(boolean enc, byte[] b, int c){
		if(enc){
			for(int i=0;i<b.length;i++){
				b[i] = (byte)((b[i]+c+i));
			}
			return b;
		}else{
			for(int i=b.length-1;i>-1;i--){
				b[i] = (byte)((b[i]-c-i));
			}
			return b;
		}
	}
	
	public byte[] progressive(boolean enc, byte[] b, int c){
		if(enc){
			int last = c;
			for(int i=0;i<b.length;i++){
				b[i] = (byte)((b[i]+last));
				last = b[i];
			}
			return b;
		}else{
			int prev = 0;
			for(int i=b.length-1;i>-1;i--){
				if(i-1 > -1){
					prev = b[i-1];
				}else{
					prev = c;
				}
				b[i] = (byte)((b[i]-prev));
			}
			return b;
		}
	}
	
	//displayer methods
	public void ascii(byte[] b){
		String build = "";
		for(int i=0;i<b.length;i++){
			build += (char)b[i];
		}
		System.out.println(build);
	}
	
	public void display(byte[] b){
		String build = "";
		for(int i=0;i<b.length;i++){
			build += b[i]+" ";
		}
		System.out.println(build);
	}
	
}
