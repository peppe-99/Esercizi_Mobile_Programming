package com.example.quiz;

public class Quesito {

    private String testoQuesito;
    private boolean risposto;
    private boolean contata;

    public Quesito(String testoQuesito, boolean risposto) {
        this.testoQuesito = testoQuesito;
        this.risposto = risposto;
        this.contata = false;
    }

    public String getTestoQuesito() { return testoQuesito; }

    public void setTestoQuesito(String testoQuesito) { this.testoQuesito = testoQuesito; }

    public boolean getRisposto() { return risposto; }

    public void setRisposto(boolean risposto) { this.risposto = risposto; }

    public boolean getContata() { return contata; }

    public void setContata(boolean contata) { this.contata = contata; }
}
