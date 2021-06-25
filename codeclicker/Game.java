package com.example.codeclicker;

public class Game {
    private int ponto,valorClick,quantidadeUpgrade,programador;
    private boolean vitoria;
    private static Game instacia;
    Game(){
        ponto = 0;
        valorClick = 1;
        quantidadeUpgrade = 1;
        programador = 1;
    }
    public String getPonto(){
        return ""+ponto;
    }
    public String getValorClick(){
        return ""+valorClick;
    }

    public int getProgramador() {
        return programador-1;
    }
    public int getQuantidadeUpgrade() {
        return quantidadeUpgrade-1;
    }

    public int getValorUpgrade(){
        return (quantidadeUpgrade*100)*quantidadeUpgrade;
    }
    public int getValorProg(){
        return (100*programador)*programador;
    }
    public void contaPonto(){
        ponto = ponto+valorClick;
    }
    public void codar(){
        if(programador != 1){
        ponto = ponto + ((programador*2)*programador);
        }
    }
    public void upgradeClick(){
        if(ponto>=getValorUpgrade()){
            ponto = ponto - getValorUpgrade();
            valorClick = (((5*quantidadeUpgrade)*quantidadeUpgrade));
            quantidadeUpgrade = quantidadeUpgrade + 1;

            Sound.getInstance().playUpgradeSound();
            Typer.getInstance().addClickAmount();
        } else {
            Sound.getInstance().playWrongSound();
        }
    }
    public void upProgramador(){
        if(ponto>=getValorProg()){
            ponto = ponto - getValorProg();
            programador = programador + 1;

            Sound.getInstance().playUpgradeSound();
            Typer.getInstance().addAutomaticAmount();
        } else {
            Sound.getInstance().playWrongSound();
        }
    }
    public boolean validaVitoria(){
        if(ponto >= 1000000){
          vitoria = true;
        }
        if (vitoria){
            vitoria = false;
            return true;
        }
        return false;
    }
    public void setInstacia(){
        instacia = null;
    }
    public static Game getInstance(){
        if(instacia == null){
            return instacia = new Game();
        }
        return instacia;
    }
}
