//Diario de Aventuras

import java.util.LinkedList;

//Clase que representa un rescate con el nombre de la criatura y si fue exitoso o no

class Rescate {
    private String criatura;
    private boolean exitoso;

    public Rescate(String criatura, boolean exitoso) {
        this.criatura = criatura;
        this.exitoso = exitoso;
    }

    public String getCriatura() {
        return criatura;
    }

    public boolean isExitoso() {
        return exitoso;
    }

    public void setCriatura(String criatura) {
        this.criatura = criatura;
    }

    @Override
    public String toString() {
        return "Rescate: " + criatura + " - " + (exitoso ? "Exitoso" : "Fallido");
    }
}

//TDA DiarioRescates, administra los rescates permitiendo agregar, eliminar, buscar y modificar.

class DiarioRescates {
    private LinkedList<Rescate> rescates;

    public DiarioRescates() {
        rescates = new LinkedList<>();
    }

    //Simulación de una barra de carga
    private void mostrarCarga(String mensaje) {
        System.out.print(mensaje);
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500);
                System.out.print("..");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(" ");
    }

    //Registrar un nuevo rescate
    public void agregarRescate(String criatura, boolean exitoso) {
        mostrarCarga("Registrando rescate");
        rescates.addLast(new Rescate(criatura, exitoso));
        
    }

    //Eliminar el último rescate si en dado caso falló
    public void descartarUltimoRescateFallido() {
        if (!rescates.isEmpty() && !rescates.getLast().isExitoso()) {
            mostrarCarga("Eliminando rescate fallido");
            rescates.removeLast();
        } else {
            System.out.println("No hay rescates fallidos recientes para eliminar.");
        }
        System.out.println();
    }

    //Mostrar todos los rescates en orden de finalización
    public void listarRescates() {
        mostrarCarga("Cargando lista de rescates");
        if (rescates.isEmpty()) {
            System.out.println("No hay rescates registrados.");
            return;
        }
        System.out.println();
        System.out.println("Lista de rescates:");
        for (Rescate rescate : rescates) {
            System.out.println(rescate);
        }
    }

    //Buscar si una criatura específica fue rescatada con éxito
    public boolean verificarRescate(String criatura) {
        mostrarCarga("Buscando en el diario");
        return rescates.stream().anyMatch(r -> r.getCriatura().equalsIgnoreCase(criatura) && r.isExitoso());
    }

    //Editar el nombre de un rescate
    public boolean modificarRescate(String criaturaAntigua, String criaturaNueva) {
        mostrarCarga("Modificando rescate");
        System.out.println();
        for (Rescate rescate : rescates) {
            if (rescate.getCriatura().equalsIgnoreCase(criaturaAntigua)) {
                rescate.setCriatura(criaturaNueva);
                return true;
            }
        }
        return false;
    }
}

//Clase principal para probar el TDA

public class DiariodeAventuras {
    public static void main(String[] args) {
        DiarioRescates diario = new DiarioRescates();

        //Agregar rescates de distintas criaturas fantásticas
        diario.agregarRescate("Quimera atrapada en una cueva", true);
        diario.agregarRescate("Pegaso herido en un bosque encantado", true);
        diario.agregarRescate("Kraken atrapado en redes de pesca", false);
        diario.listarRescates();

        //Eliminar el último rescate si en dado caso falló
        diario.descartarUltimoRescateFallido();
        diario.listarRescates();

        //Buscar un rescate
        System.out.println("\n¿Se rescató al 'Pegaso'?: " + 
            (diario.verificarRescate("Pegaso herido en un bosque encantado") ? "Sí, fue rescatado." : "No, no fue rescatado."));
        
        System.out.println("¿Se rescató al 'Kraken'?: " + 
            (diario.verificarRescate("Kraken atrapado en redes de pesca") ? "Sí, fue rescatado." : "No, no fue rescatado."));

        //Editar un rescate
        boolean modificado = diario.modificarRescate("Quimera atrapada en una cueva", "Liberar una Quimera en su hábitat");
        if (modificado) {
            System.out.println("Rescate modificado con éxito.");
        } else {
            System.out.println("No se encontró el rescate a modificar.");
        }
        diario.listarRescates();
    }
}





        
        


    








