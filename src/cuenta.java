public class cuenta {
    private String nombreUsuario;
    private String contrasena;
    private double balance;

    public cuenta(String username, String password) {
        this.nombreUsuario = username;
        this.contrasena = password;
        this.balance = 0.0;
    }
    
    public boolean verificarContrasena(String password) {
        return this.contrasena.equals(password);
    }


    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public double getBalance() {
        return balance;
    }

    public void depositar(double cantidad) {
        balance += cantidad;
        System.out.println("Depósito exitoso. nuevo saldo: " + balance);
    }

    public void retirar(double cantidad) throws Fondosinsuficientesexcepcion {
        if (cantidad > balance) {
            throw new Fondosinsuficientesexcepcion("Saldo insuficiente");
        }
        balance -= cantidad;
        System.out.println("Retiro exitoso. Su nuevo saldo ahora es de: " + balance);
    }

    public void transferir(cuenta recipiente, double cantidad_dos) throws Fondosinsuficientesexcepcion {
        retirar(cantidad_dos);
        recipiente.depositar(cantidad_dos);
        System.out.println("Transferencia exitoso. Usted transfirió: " + cantidad_dos);
    }
}

