package source;

import java.math.BigDecimal;
import java.util.LinkedList;

public class Molecula {
	
	
	public Molecula(String nomeMolecula){
		this.nome = nomeMolecula;
	}

	String nome;
	LinkedList<Atom> atomos = new LinkedList<Atom>();
	int status_normatizado = 0;
	BigDecimal distanceAxisX = new BigDecimal("0");
	BigDecimal distanceAxisY = new BigDecimal("0"), distanceAxisZ = new BigDecimal("0");
	
	BigDecimal max_x= new BigDecimal("0"), max_y= new BigDecimal("0"), 
			max_z= new BigDecimal("0");
	BigDecimal min_x= new BigDecimal("0"), 
			min_y= new BigDecimal("0"), min_z= new BigDecimal("0");
	
	BigDecimal distanceX = new BigDecimal("0"), 
			distanceY = new BigDecimal("0"), 
			distanceZ = new BigDecimal("0");
	
	
	public void addAtom(Atom atom){
		
		//trata a coordenada x
		if(atomos.size()<1){
			min_x = atom.getCoox();max_x=atom.getCoox();
		}
		
		if(min_x.compareTo(atom.getCoox())==1 && !(atomos.size()<1)) {
			min_x = atom.getCoox();
		}
		if(max_x.compareTo(atom.getCoox())==-1 && !(atomos.size()<1)){
			max_x = atom.getCoox();
		}
		distanceX = max_x.pow(2).subtract(min_x.pow(2));
		long distance = distanceX.longValue();
		//this.distanceAxisX = Math.sqrt(distance);
		this.distanceAxisX = max_x.subtract(min_x);
		
		//trata a coordenada y
		if(atomos.size()<1){
			min_y = atom.getCooy();max_y=atom.getCooy();
		}
		if(min_y.compareTo(atom.getCooy())==1 && !(atomos.size()<1)) {
			min_y = atom.getCooy();
		}
		if(max_y.compareTo(atom.getCooy())==-1 && !(atomos.size()<1)){
			max_y = atom.getCooy();
		}
		distanceY = max_y.pow(2).subtract(min_y.pow(2));
		long distance1 = distanceY.longValue();
		this.distanceAxisY = max_y.subtract(min_y);
				
				
		//trata a coordenada z
		if(atomos.size()<1){
			min_z = atom.getCooz();max_z=atom.getCooz();
		}
		
		if(min_z.compareTo(atom.getCooz())==1) {
			min_z = atom.getCooz();
		}
		if(max_z.compareTo(atom.getCooz())==-1){
			max_z = atom.getCooz();
		}
		distanceZ = max_z.pow(2).subtract(min_z.pow(2));
		long distance2 = distanceZ.longValue();
		this.distanceAxisZ = max_z.subtract(min_z);
	
		System.out.println("Distances: x:"+this.distanceAxisX+" [minX: "+this.min_x+" maxX:"+this.max_x+"] "
				                    + "y:"+this.distanceAxisY+" [minY: "+this.min_y+" maxY:"+this.max_y+"] "
				                    + "z:"+this.distanceAxisZ+" [minZ: "+this.min_z+" maxZ:"+this.max_z+"]");
		atomos.add(atom);
		
	}
	
}
