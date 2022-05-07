package projeto;

import java.io.IOException;
import java.io.Serializable;
//import java.util.ArrayList;

public class classifier implements Serializable {
	
	private static final long serialVersionUID = 1L;
	MRFT[] markov;
	double[] freq;

	public classifier(MRFT[] markov, double[] freq) {
		super();
		this.markov = markov;
		this.freq = freq;
	}
	
	public int Classify(int[] v) {
		double max=0;
		int c=0;
		for (int i = 0; i < markov.length; i++) {
			double j=markov[i].Prob(v)*freq[i];
			if(j>max) {
				max=j;
				c=i;
			}
		}
		return c;
	}





	public static void main(String[] args) {
		try {
			Dataset B = new Dataset("bcancer.csv");
			MRFT m=new MRFT(B.Fiber(0),0.2);
			MRFT n=new MRFT(B.Fiber(1),0.2);
			MRFT[] lista= new MRFT[] {m,n};
			double[] freqa = new double[] {B.Count(new int[]{10},new int[]{0}),B.Count(new int[]{10},new int[]{1})};
			classifier c=new classifier(lista,freqa);
			int classificacao = c.Classify(new int[]{1,0,1,3,2,0,1,1,1,1});
			System.out.println(classificacao);
			double TT=0; // total de classificações corretas
			for (int i = 0; i < B.Size(); i++) {
				int[] fu= new int[B.dados.get(i).length -1];
				for (int j = 0; j < B.dados.get(i).length -1; j++) {
					fu[j]=B.dados.get(i)[j];
				}
				if (B.dados.get(i)[B.dados.get(i).length-1]==c.Classify(fu)) {
					TT+= +1;
				}
			}
			System.out.println(TT/B.Size()); //Percentagem de classificações corretas
			} catch (IOException e) {
				e.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}

	}

}
