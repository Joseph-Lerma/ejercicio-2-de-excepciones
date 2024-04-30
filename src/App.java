import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {
    private static Map<String, cuenta> accounts = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("Bienvenido usuario, que desea hacer? \n1. Crear cuenta\n2. Acceder a cuenta\n3. Salir");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    crearcuenta();
                    break;
                case 2:
                    accesocuenta();
                    break;
                case 3:
                    exit = true;
                    System.out.println("Usted ha cerrado sesión.");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo");
            }
        }
    }

    private static void crearcuenta() {
        System.out.println("Ingrese un nombre de usuario:");
        String username = scanner.nextLine();
        if (accounts.containsKey(username)) {
            System.out.println("Ese nombre de usuario ya se encuentra en uso.");
            return;
        }
        System.out.println("Ingrese una contraseña:");
        String password = scanner.nextLine(); // Solicitar contraseña
        cuenta account = new cuenta(username, password); // Pasar la contraseña al constructor
        accounts.put(username, account);
        System.out.println("Cuenta creada con éxito para " + username);
    }
    private static void accesocuenta() {
        System.out.println("Ingrese nombre de usuario:");
        String nombreusuario = scanner.nextLine();
        cuenta cuenta = accounts.get(nombreusuario);
        if (cuenta == null) {
            System.out.println("El usuario no existe. Por favor, cree una cuenta primero.");
            return;
        }

        System.out.println("Ingrese la contraseña:");
        String password = scanner.nextLine(); // Solicitar contraseña
        if (!cuenta.verificarContrasena(password)) {
        System.out.println("Contraseña incorrecta.");
        return;
       }
        boolean logout = false;
        while (!logout) {
            System.out.println("\n1. Depositar\n2. Retirar\n3. Transferir\n4. Saldo\n5. Volver al menú principal");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    depositar(cuenta);
                    break;
                case 2:
                    retirar(cuenta);
                    break;
                case 3:
                    transferir(cuenta);
                    break;
                case 4:
                    System.out.println("Saldo actual: " + cuenta.getBalance());
                    break;
                case 5:
                    logout = true;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elija una opción válida.");
            }
        }
    }

    private static void depositar(cuenta cuenta) {
        System.out.println("Ingrese la cantidad a depositar:");
        double cantidad_dos = scanner.nextDouble();
        cuenta.depositar(cantidad_dos);
    }

    private static void retirar(cuenta account) {
        System.out.println("Ingrese la cantidad a retirar:");
        double amount = scanner.nextDouble();
        try {
            account.retirar(amount);
        } catch (Fondosinsuficientesexcepcion e) {
            System.out.println(e.getMessage());
        }
    }

    private static void transferir(cuenta sender) {
        System.out.println("Ingrese el nombre de usuario del destinatario:");
        String recipientUsername = scanner.nextLine();
        cuenta recipiente = accounts.get(recipientUsername);
        if (recipiente == null) {
            System.out.println("El destinatario no existe.");
            return;
        }
        System.out.println("Ingrese la cantidad a transferir:");
        double cantidad_dos = scanner.nextDouble();
        try {
            sender.transferir(recipiente, cantidad_dos);
        } catch (Fondosinsuficientesexcepcion e) {
            System.out.println(e.getMessage());
        }
    }
}
