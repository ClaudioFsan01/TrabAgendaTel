package Controle;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import Modelo.Contato;
import Servico.AgendaDao;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TabelaAgendaModelo extends AbstractTableModel {

    private List<Contato> lista=new ArrayList<Contato>();

    public TabelaAgendaModelo() {
    }

    public Contato getContato(int linha) {
        return lista.get(linha);
    }
    
    @Override
    public int getRowCount() {
        if(lista!=null) return lista.size();
        else return 0;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch(columnIndex) {
            case 0: 
                return "Nome";
            case 1:
                return "Telefone";
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Contato contato=lista.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return contato.getNome();
            case 1:
                return contato.getTelefone();
            default:
                return null;
        }
    }

    public void remove(int linha) {
    	Contato contato = lista.get(linha);
        lista.remove(linha);
        new AgendaDao().excluir(contato);
        
        
        fireTableDataChanged();
    }

    public void setContato(Contato contatoOriginal, Contato contatoEditado, int linha) {
        lista.set(linha, contatoEditado);
        
        String telefoneOriginal = contatoOriginal.getTelefone();
        new AgendaDao().alterarContato(telefoneOriginal, contatoEditado);
        fireTableDataChanged();
        
    }
    
    public void adicionarContato(Contato contato) {
        lista.add(contato);
        new AgendaDao().adicionarContato(contato);
        fireTableDataChanged();
    }
    
    public void listarContatos() {
      this.lista = new AgendaDao().obterTodos();    	
    	fireTableDataChanged();
    }
    
    
   
 /*   void carregarContatos() {
    
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("agenda.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            lista = (ArrayList<Contato>) ois.readObject();
            fireTableDataChanged();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro de persistência.");
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(TabelaAgendaModelo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    	
    	
    }*/

  /*  public void persistirContatos() {
        try {
        	
            FileOutputStream fos = new FileOutputStream("agenda.ser");
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(lista);
            oos.close();
        	
        	
        } catch (IOException ex) {
            
        }

    }*/
    
    
}