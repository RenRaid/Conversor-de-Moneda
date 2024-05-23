package Conversor.Principal;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        ConsultaRate consulta = new ConsultaRate();
        String opciones = """
                1) Dolar  >>>  Peso Argentino
                2) Peso Argentino  >>>  Dolar
                3) Dolar  >>>  Real Brasileño
                4) Real Brasileño  >>>  Dolar
                5) Dolar  >>>  Peso Colombiano
                6) Peso Colombiano  >>>  Dolar
                7) Ver Historial de Conversiones
                8) Salir
                *******************************************""";

        String[] baseRates = {"USD", "ARS", "USD", "BRL", "USD", "COP"};
        String[] targetRates = {"ARS", "USD", "BRL", "USD", "COP", "USD"};

        System.out.println("""
                ******************************************
                Benvenido/a al Conversor de Monedas.
                Por favor elija  una opcion:
                ******************************************""");
        System.out.println(opciones);
        Scanner conversion = new Scanner(System.in);


        try {
            int seleccion = Integer.parseInt(conversion.nextLine());
            ArrayList<String> historial = new ArrayList<>();

            while (seleccion < 7) {
                MonedaRate result = consulta.rateConversion(baseRates[seleccion - 1], targetRates[seleccion - 1]);
                System.out.println("Ingrese el monto a convertir.");
                double monto = Double.parseDouble(conversion.nextLine());

                Double total = monto * Double.parseDouble(result.conversion_rate());

                LocalDateTime myDateObj = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate = myDateObj.format(myFormatObj);

                Formatter numberFormat = new Formatter();

                System.out.println();
                System.out.println(formattedDate);
                System.out.println("El monto: " + monto + " [" + result.base_code() + "] equivale a: "
                        + numberFormat.format("%.2f", total) + " [" + result.target_code() + "]");
                System.out.println();
                String transaccion = ("Monto:" + monto + "[" + result.base_code() + "] equivalente:"
                        + String.format("%.2f",total) + "[" + result.target_code() + "]");

                historial.add("********************");
                historial.add(formattedDate);
                historial.add(transaccion);


                System.out.println(opciones);
                seleccion = Integer.parseInt(conversion.nextLine());

            }

               if (seleccion==7){
                   if(historial.isEmpty()){
                       System.out.println("No existen registros de conversiones");
                   }else {
                       System.out.println("*** HISTORIAL ***");
                       for (String s : historial) {
                           System.out.println(s);
                       }
                   }

               }
            while (seleccion > 8) {
                System.out.println("La opcion seleccionada no es valida! Intentelo de nuevo!");
                seleccion = Integer.parseInt(conversion.nextLine());
            }
        }catch (NumberFormatException e){
            System.out.println("Opcion no seleccionada");
            System.out.println("Finalizando aplicacion");
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
        }

        System.out.println("""
                  ***************************************************
                   Gracias por utilizar el conversor de monedas.
                  ***************************************************""");
    }
}



