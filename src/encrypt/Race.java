package encrypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;

public class Race {
	
	String path = "C:\\Users\\Emmett\\Desktop\\source - texture\\encrypt\\yo.txt";
	public String plain = "secret message";
	
	public int[] fromfile(File f){
		try {
			FileInputStream o = new FileInputStream(f);
			int yo = o.available();
			int[] get = new int[yo];
			for(int i=0;i<yo;i++){
				get[i] = o.read();
			}
			o.close();
			return get;
		} catch (Exception ex){}
		return null;
	}
	
	public void tofile(File f, int[] b){
		try {
			FileOutputStream o = new FileOutputStream(f);
			int yo = b.length;
			for(int i=0;i<yo;i++){
				o.write(b[i]);
			}
			o.close();
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	public int[] toint(String m){
		int lo = m.length();
		byte[] b = m.getBytes();
		int[] ay = new int[lo];
		for(int i=0;i<lo;i++){
			ay[i] = b[i];
		}
		return ay;
	}
	
	public static void main(String[] args) {
		new Race();
	}
	
	public String ouc(boolean enc, String s){
		String[] ba = bases(s);
		if(enc){
			return ba[0]+ba[1]+"enc"+ba[2];
		}else{
			return ba[0]+ba[1]+"dec"+ba[2];
		}
	}
	
	public Race(){
		System.out.println("getting file");
		File inp = new File(path);
		int[] mes = fromfile(inp);
		
		System.out.println("encrypting");
		int[][] tion = {
				{0,12},
				{4,78},
				{2,56},
				{1,38},
				{2,144},
				{4,1},
				{1,90}
		};
		mes = pipe(mes,tion);
		/*mes = shift(true,mes,56);
		//mes = trash(true,mes,0);
		mes = dance(true,mes,0);
		mes = escalate(true,mes,34);
		mes = progressive(true,mes,12);
		mes = shift(true,mes,32);
		//mes = trash(true,mes,0);
		mes = dance(true,mes,0);
		mes = escalate(true,mes,99);
		mes = progressive(true,mes,27);*/
		
		System.out.println("writing");
		tofile(new File(ouc(true,path)),mes);
		
		System.out.println("done");
	}
	
	public int[] pipe(int[] sta, int[][] ruc){
		int runs = ruc.length;
		for(int i=0;i<runs;i++){
			try {
				System.out.println(this.getClass().getMethods()[2].getName());
				this.getClass().getMethods()[ruc[i][0]+1].invoke(this.getClass(), new Object[]{true,sta,ruc[i][1]});
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException ex) {
				ex.printStackTrace();
			}
		}
		return sta;
	}
	
	//start of encrypting methods
	public int[] shift(boolean enc, int[] b, int c){//0
		int yo = b.length;
		if(enc){
			for(int i=0;i<yo;i++){
				b[i] = (b[i]+c)%256;
			}
			return b;
		}else{
			for(int i=0;i<yo;i++){
				b[i] = (b[i]-c)%256;
			}
			return b;
		}
	}
	
	public int[] escalate(boolean enc, int[] b, int c){//1
		int yo = b.length;
		if(enc){
			for(int i=0;i<yo;i++){
				b[i] = (b[i]+c+i)%256;
			}
			return b;
		}else{
			for(int i=yo-1;i>-1;i--){
				b[i] = (b[i]-c-i)%256;
			}
			return b;
		}
	}
	
	public int[] progressive(boolean enc, int[] b, int c){//2
		int yo = b.length;
		if(enc){
			int last = c;
			for(int i=0;i<yo;i++){
				b[i] = (b[i]+last)%256;
				last = b[i];
			}
			return b;
		}else{
			int prev = 0;
			for(int i=yo-1;i>-1;i--){
				if(i-1 > -1){
					prev = b[i-1];
				}else{
					prev = c;
				}
				b[i] = (b[i]-prev)%256;
			}
			return b;
		}
	}
	
	public int[] trash(boolean enc, int[] b, int c){//3
		int yo = b.length;
		if(enc){
			int nt = yo*2;
			int[] ovtm = new int[nt];
			for(int i=0;i<nt;i++){
				if(i%2==0){
					ovtm[i] = b[i/2];
				}else{
					ovtm[i] = (int)(Math.random()*256);
				}
			}
			return ovtm;
		}else{
			int ac = yo/2;
			int[] col = new int[ac];
			for(int i=0;i<ac;i++){
				col[i] = b[i*2];
			}
			return col;
		}
	}
	
	public int[] dance(boolean enc, int[] b, int c){//4
		int yo = b.length;
		for(int i=0;i<yo;i+=2){
			try{
				int temp = b[i];
				b[i] = b[i+1];
				b[i+1] = temp;
			}catch(Exception ex){}
		}
		return b;
	}
	
	//displayer methods
	public void bas(int[] b){
		int yo = b.length;
		for(int i=0;i<yo;i++){
			System.out.print(b[i]+" ");
		}
		System.out.println();
	}
	
	public void utf(int[] b){
		int yo = b.length;
		for(int i=0;i<yo;i++){
			System.out.print((char)b[i]);
		}
		System.out.println();
	}
	
	public String[] bases(String s){
		String[] l = new String[3];
		int[] mark = new int[l.length];
		String temp = "";
		int otm = s.length();
		for(int i=otm-1;i >= 0;i--){
			char u = s.charAt(i);
			temp += u;
			if(u=='.'){
				mark[1] = i;
				String t = "";
				int ttm = temp.length()-1;
				for(int v=ttm;v >= 0;v--){
					t += temp.charAt(v);
				}
				l[2] = t;
				temp = "";
			}
			if(u=='\\'){
				mark[0] = i;
				temp = "";
				break;
			}
		}
		for(int i=0;i<otm;i++){
			char u = s.charAt(i);
			temp += u;
			if(i==mark[0]){
				l[0] = temp;
				temp = "";
			}
			if(i==mark[1]-1){
				l[1] = temp;
				break;
			}
		}
		return l;
	}
}
