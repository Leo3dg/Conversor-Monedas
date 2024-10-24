public class ConsultaDivisas {
    public class consultaDivisas
    {
        private String Codigo;
        private String Pais;
        private String Divisa;

        public String getDivisa() {return Divisa;}

        public void setDivisa(String divisa) {Divisa = divisa;}

        public String getCodigo() {
            return Codigo;
        }

        public void setCodigo(String codigo) {
            Codigo = codigo;
        }

        public String getPais() {
            return Pais;
        }

        public void setPais(String pais) {
            Pais = pais;
        }
        @Override
        public String toString() {
            return  "Pais: " + Pais + '\n' +
                    "Moneda: " + Codigo + '\n' +
                    "Divisa: " + Divisa + '\n';
        }
    }
}
