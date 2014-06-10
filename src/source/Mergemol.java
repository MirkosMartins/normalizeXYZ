package source;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;

public class Mergemol {
	
	Principal p = new Principal();
	
	LinkedList<Atom> molecula1 = new LinkedList<Atom>();
	LinkedList<Atom> molecula2 = new LinkedList<Atom>();
	BigDecimal disBtwMol = new BigDecimal("0.00");
	
	
	public void processaArqEntrada(String filename) throws IOException{
		@SuppressWarnings("resource")
		BufferedReader input = new BufferedReader(new FileReader(filename));
		String linha = input.readLine();
		
		//alocar molécula para cada arquivo de entrada
		Molecula molecula = new Molecula(filename);
		
		while(linha!=null){			
			linha = linha.replaceAll("\\s+"," ");//remove excesso de espaços em branco
			String tokens[] = linha.split(" ");
			if(tokens.length>1){
				//System.out.println("Trabalhando na linha: "+linha);
				//System.out.println("Numero de tokens: "+tokens.length);
				Atom atomo = new Atom();
				atomo.setNumAtom(Integer.valueOf(tokens[0]));
				//Double coo_x = Double.valueOf(tokens[1]);
				//System.out.println(tokens[1]);
				BigDecimal cooB_x = new BigDecimal(tokens[1].toString());
				/*if(cooB_x.compareTo(p.min_x)==-1)p.min_x=cooB_x;//setar maior e menor valores da coordenada x
				if(cooB_x.compareTo(p.max_x)==1)p.max_x=cooB_x;//para toda a estrutura do arquivo xyz
*/				atomo.setCoox(cooB_x);
				
				//Double coo_y = Double.valueOf(tokens[2]);
				BigDecimal cooB_y = new BigDecimal(tokens[2]);
				/*if(cooB_y.compareTo(p.min_y)==-1)p.min_y=cooB_y;//menor que o min_y
				if(cooB_y.compareTo(p.max_y)==1)p.max_y=cooB_y;//maior que o max_y
*/				atomo.setCooy(cooB_y);
				
				//Double coo_z = Double.valueOf(tokens[3]);
				BigDecimal cooB_z = new BigDecimal(tokens[3]);
				/*if(cooB_z.compareTo(p.min_z)==-1)p.min_z=cooB_z;
				if(cooB_z.compareTo(p.max_z)==1)p.max_z=cooB_z;*/
				atomo.setCooz(cooB_z);
				//p.atomos.add(atomo);
				molecula.addAtom(atomo);
				
			}				
			linha = input.readLine();
			}
		/*System.out.println("Menores valores: "+p.min_x+" "+p.min_y+" "+p.min_z);
		System.out.println("Maiores valores: "+p.max_x+" "+p.max_y+" "+p.max_z);*/
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String arquivo = "ribbonzigzag_proc.xyz";
		Mergemol mg = new Mergemol();
		try {
			mg.processaArqEntrada(arquivo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
