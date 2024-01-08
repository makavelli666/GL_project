package fr.ufrsciencestech.projet.model;

public class Fraise implements Fruit{
    private double prix;
    private String origine;
	
    public Fraise() 
    {
        this.prix = 0.5;  //prix en euros
        this.origine="Espagne";
    }
    
    public Fraise(double prix, String origine) 
    {
	if(prix < 0)
	    this.prix = -prix;  //une solution possible pour interdire les prix negatifs
	else
	    this.prix = prix;

	if(origine.equals(""))
            this.origine = "Espagne";  //Espagne par défaut
	else
            this.origine = origine;   
    }

    @Override
    public double getPrix(){
	return prix;
    }

    @Override
    public void setPrix(double prix){
	this.prix=prix;
    }

    @Override
    public String getOrigine(){
	return origine;
    }
 
    @Override
    public void setOrigine(String origine){
	this.origine=origine;
    }

    @Override
    public String toString(){
        return "Fraise de " + origine + " a " + prix + " €";
    }

    @Override
    public boolean equals(Object o){  //predicat pour tester si 2 Fraises sont equivalentes
        if(o != null && getClass() == o.getClass()){
            Fraise or = (Fraise) o;
            return (prix == or.prix && origine.equals(or.origine));
        }
        return false;
    }

    @Override
    public boolean isSeedless() {  //predicat indiquant qu'un fruit a des pepins
        return false;
    }

}
