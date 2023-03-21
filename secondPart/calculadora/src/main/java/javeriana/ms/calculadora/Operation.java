package javeriana.ms.calculadora;


public class Operation {



    String user;
    String operacion;
    int parametroUno;
    int parametroDos;

    public Operation() {

    }

    public Operation(String user, String operacion, int parametroUno, int parametroDos) {
        this.user = user;
        this.operacion = operacion;
        this.parametroUno = parametroUno;
        this.parametroDos = parametroDos;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public int getParametroUno() {
        return parametroUno;
    }

    public void setParametroUno(int parametroUno) {
        this.parametroUno = parametroUno;
    }

    public int getParametroDos() {
        return parametroDos;
    }

    public void setParametroDos(int parametroDos) {
        this.parametroDos = parametroDos;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "user='" + user + '\'' +
                ", operacion='" + operacion + '\'' +
                ", parametroUno=" + parametroUno +
                ", parametroDos=" + parametroDos +
                '}';
    }
}
