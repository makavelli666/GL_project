package fr.ufrsciencestech.projet.model;

public class Cerise implements Fruit{
    private double prix;
    private String origine;
	
    public Cerise() 
    {
        this.prix = 0.6;  //prix en euros
        this.origine="Italie";
    }
    
    public Cerise(double prix, String origine) 
    {
	if(prix < 0)
	    this.prix = -prix;  //une solution possible pour interdire les prix negatifs
	else
	    this.prix = prix;

	if(origine.equals("") || origine.isEmpty())
            this.origine = "Italie";  //Espagne par défaut
	else
            this.origine = origine;   
    }

    @Override
    public double getPrix(){
	return prix;
    }

    @Override
    public void setPrix(double prix){
    	if(prix >= 0)
		this.prix=prix;
	else 
		System.out.println("Il n'est pas possible de donner un prix négatif");
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
        return "Cerise de " + origine + " a " + prix + " €";
    }

    @Override
    public boolean equals(Object o){  //predicat pour tester si 2 oranges sont equivalentes
        if(o != null && getClass() == o.getClass()){
            Cerise cer = (Cerise) o;
            return (prix == cer.prix && origine.equals(cer.origine));
        }
        return false;
    }

    @Override
    public boolean isSeedless() {  //predicat indiquant qu'un fruit a des pepins
        return true;
    }

}

