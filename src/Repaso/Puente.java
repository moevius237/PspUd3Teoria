package Repaso;
//Va a controlar cuantas personas y peso maximo admite el puente
//Siempre en lña misma direccion se tiene que cumplir una serie de requisitos
//No pueden pasar mas de 3 personas a la vez y no puede haber mas de 200 kg

//Para la simulacion vamos a tener un clase persona que seran los hilos
//Clase puente es una clase normal aqui definimos el peso maximo y el numero de personas maximas
//tendremos tambien variable para el peso y n um personas que hay en el puente actualmente

//Tenmos un metodo autorizacionPaso , la pasaremos una persona y nos devolvera
//si esta autorizado o no a pasar mas lo que ya tengo

//Otro metodo  salirppuente () le pasamos una persona sacara a esa persona del puente
public class Puente {
    private static final int persomax = 3;
    private static final int kgmax= 200;

    private  int peso = 0;
    private int numperso = 0;

    private synchronized boolean autorizacinPaso(Persona persona){
    if ((Persona.getPeso() + this.peso) > kgmax || numperso < persomax){
        numperso++;
        peso+=Persona.getPeso();
        return true;
    }
        return false;
    }
    private synchronized void salirPuente(Persona persona){
        numperso--;
        peso -= Persona.getPeso();
    }

}
