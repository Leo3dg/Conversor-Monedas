import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException {
        String opcionOrigenPais, revisionOrigen, opcionDestinoPais, revisionDestino;
        while (true){
            System.out.println("\t-----------------------------------");
            System.out.println("\t| **** "+Variables.fGris+"CONVERSOR DE MONEDAS"+Variables.b+" ***** |");
            System.out.println("\t-----------------------------------");
            System.out.println("\t|  1.- Conversion de Monedas      |");
            System.out.println("\t|  2.- Ver listado de paises      |");
            System.out.println("\t|  3.- Salir                      |");
            System.out.println("\t-----------------------------------");
            System.out.print("\t Ingrese una Opcion: ");
            Scanner leer = new Scanner(System.in);
            char opcion = leer.next().charAt(0);
            switch (opcion){
                case '1':
                    opcionOrigenPais="";
                    revisionOrigen="";
                    opcionDestinoPais="";
                    revisionDestino="";
                    while (revisionOrigen.equals(opcionOrigenPais.toUpperCase())){
                        System.out.print("\t Ingrese el Pais de la moneda que desea convertir: ");
                        Scanner origenPais = new Scanner(System.in);
                        opcionOrigenPais = origenPais.nextLine();
                        revisionOrigen = opcionOrigenPais.toUpperCase();
                        opcionOrigenPais = Funciones.codigoMoneda(opcionOrigenPais.toUpperCase());
                    }
                    while (revisionDestino.equals(opcionDestinoPais.toUpperCase())) {
                        System.out.print("\t Ingrese el Pais de la moneda que va a convertir: ");
                        Scanner destinoPais = new Scanner(System.in);
                        opcionDestinoPais = destinoPais.nextLine();
                        revisionDestino = opcionDestinoPais.toUpperCase();
                        opcionDestinoPais = Funciones.codigoMoneda(opcionDestinoPais.toUpperCase());
                    }
                    consultaMoneda consulta = new consultaMoneda();
                    Monedas monedas = consulta.buscaMonedas(opcionDestinoPais,opcionOrigenPais);
                    System.out.println(Variables.rojo+"\t El valor de 1 "+opcionDestinoPais+" son "+monedas.conversion_rate()+" "+opcionOrigenPais+Variables.b);
                    Funciones.convertir(Double.parseDouble(monedas.conversion_rate()), opcionOrigenPais, opcionDestinoPais);
                    break;

                case '2':
                    Gson gson = new Gson();
                    String fichero = "";
                    try (BufferedReader br = new BufferedReader(new FileReader("codigopaises.json"))) {
                        String linea;
                        while ((linea = br.readLine()) != null) {
                            fichero += linea;
                        }
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                    Type userListType = new TypeToken<ArrayList<ConsultaDivisas.consultaDivisas>>() {
                    }.getType();
                    ArrayList<ConsultaDivisas.consultaDivisas> userArray = gson.fromJson(fichero, userListType);
                    for (ConsultaDivisas.consultaDivisas consultaDivisas : userArray) {
                        System.out.println(consultaDivisas.toString());
                    }
                    break;

                case '3':
                    System.out.println("\t Cerrando Programa");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\t Opcion incorrecta");
                    break;
            }
        }
    }
}
