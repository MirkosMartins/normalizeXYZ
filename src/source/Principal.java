package source;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;

public class Principal {
	
	
	LinkedList<Estrutura> atomos = new LinkedList<Estrutura>();
	int status_normatizado = 0;
	
	BigDecimal max_x= new BigDecimal("0"), max_y= new BigDecimal("0"), 
			max_z= new BigDecimal("0");
	BigDecimal min_x= new BigDecimal("0"), 
			min_y= new BigDecimal("0"), min_z= new BigDecimal("0");

	public static void main(String[] args) {
		String arquivo = "ribbonzigzag.xyz";
		String output = "ribbonzigzag_proc.xyz";
		Principal p = new Principal();
		try {
			p.processaArqEntrada(arquivo, p);
			p.normalizaEstrutura(p);
			p.gravaArquivoSaida(output, p);
		} catch (IOException e) {
			System.out.println("Deu erro: "+e.getMessage());
		}

	}
	
	
	public void gravaArquivoSaida(String filename, Principal p) throws IOException{
		BufferedWriter output = new BufferedWriter(new FileWriter(filename));
		if(p.status_normatizado==1){
			for(int x = 0; x < p.atomos.size(); x++){
				output.write(p.atomos.get(x).getNumAtom()+" "+p.atomos.get(x).getCoox().toString()+" "+p.atomos.get(x).getCooy().toString()+ " "+p.atomos.get(x).getCooz().toString()+"\n");
			}
			output.close();
		}else{
			System.out.println("Dados não normatizados no sistema. Não foi possível gravar o output.");
		}
	}
	
	
	public void normalizaEstrutura(Principal p){
		if(p.atomos.size()>0 & p.status_normatizado==0){
			BigDecimal zero = new BigDecimal("0.0");
			if(p.min_x.compareTo(zero)==-1){
				//INCREMENTA TODOS OS X PARA NORMALIZAR EM ZERO!
				System.out.println("X");
				for(int x = 0; x<p.atomos.size();x++){
					BigDecimal valuex = p.atomos.get(x).getCoox();
					p.atomos.get(x).setCoox(valuex.add(p.min_x.abs()));
				}
			}
			
			if(p.min_y.compareTo(zero)==-1){
				//INCREMENTA TODOS OS Y PARA NORMALIZAR EM ZERO!
				for(int y = 0; y<p.atomos.size();y++){
					BigDecimal valuey = p.atomos.get(y).getCooy();
					p.atomos.get(y).setCooy(valuey.add(p.min_y.abs()));
				}					
			}
			
			if(p.min_z.compareTo(zero)==-1){
				//INCREMENTA TODOS OS Z PARA NORMALIZAR EM ZERO!
				for(int z = 0; z<p.atomos.size();z++){
					BigDecimal valuez = p.atomos.get(z).getCooz();
					p.atomos.get(z).setCooz(valuez.add(p.min_z.abs()));
				}
					
			}
			System.out.println("NORMATIZADO");
		}else{
			System.out.println("Não foi encontrado nenhum átomo armazenado no sistema!");
		}
		p.status_normatizado = 1;
	}
	
	public void processaArqEntrada(String filename, Principal p) throws IOException{
		@SuppressWarnings("resource")
		BufferedReader input = new BufferedReader(new FileReader(filename));
		String linha = input.readLine();
		while(linha!=null){			
			linha = linha.replaceAll("\\s+"," ");//remove excesso de espaços em branco
			String tokens[] = linha.split(" ");
			if(tokens.length>1){
				//System.out.println("Trabalhando na linha: "+linha);
				//System.out.println("Numero de tokens: "+tokens.length);
				Estrutura atomo = new Estrutura();
				atomo.setNumAtom(Integer.valueOf(tokens[0]));
				//Double coo_x = Double.valueOf(tokens[1]);
				System.out.println(tokens[1]);
				BigDecimal cooB_x = new BigDecimal(tokens[1].toString());
				if(cooB_x.compareTo(p.min_x)==-1)p.min_x=cooB_x;//setar maior e menor valores da coordenada x
				if(cooB_x.compareTo(p.max_x)==1)p.max_x=cooB_x;//para toda a estrutura do arquivo xyz
				atomo.setCoox(cooB_x);
				
				//Double coo_y = Double.valueOf(tokens[2]);
				BigDecimal cooB_y = new BigDecimal(tokens[2]);
				if(cooB_y.compareTo(p.min_y)==-1)p.min_y=cooB_y;//menor que o min_y
				if(cooB_y.compareTo(p.max_y)==1)p.max_y=cooB_y;//maior que o max_y
				atomo.setCooy(cooB_y);
				
				//Double coo_z = Double.valueOf(tokens[3]);
				BigDecimal cooB_z = new BigDecimal(tokens[3]);
				if(cooB_z.compareTo(p.min_z)==-1)p.min_z=cooB_z;
				if(cooB_z.compareTo(p.max_z)==1)p.max_z=cooB_z;
				atomo.setCooz(cooB_z);
				p.atomos.add(atomo);
				
			}				
			linha = input.readLine();
			}
		System.out.println("Menores valores: "+p.min_x+" "+p.min_y+" "+p.min_z);
		System.out.println("Maiores valores: "+p.max_x+" "+p.max_y+" "+p.max_z);
	}

}
