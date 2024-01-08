package fr.ufrsciencestech.projet.model;

/**
 *
 * @author idira
 */
public class FabriqueFruit implements FruitFactory{
    @Override
    public Fruit creerFruit(String fruit, double prix, String origine) {
        switch(fruit.toLowerCase()) {
            case "banane":
                return new Banane(prix,origine);
            case "orange":
                return new Orange(prix,origine);
            case "fraise":
                return new Fraise(prix,origine);
            case "ananas":
                return new Ananas(prix,origine);
            case "kiwi":
                return new Kiwi(prix,origine);
            case "cerise":
                return new Cerise(prix,origine);
            default:
                System.out.println("Je ne connais pas ce fruit.");
        }
        return null;
    }
    
}
