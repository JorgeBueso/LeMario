/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jorge
 */
public class LeMario
{
    //Para almacenar el LeMario en un hashmap
    HashMap<String, LeMario> lemario = new HashMap<>();
     
    
    //quita tildes, diéresis y cedillas . No quita ñ , ¿ , ¡ , º , Ü .
    public static String quitaMierda(String lista)
    {
        String quita = null;
        if (lista != null) 
        {
            String valor = lista;
            valor = valor.toUpperCase();
        
            // Normalizar texto para eliminar tildes, diéresis y cedillas 
            quita = Normalizer.normalize(valor, Normalizer.Form.NFD);
            
            // Quitar caracteres no ASCII excepto ñ , ¿ , ¡ , º , Ü .
            quita = quita.replaceAll("[^\\p{ASCII}(N\u0303)(n\u0303)(\u00A1)(\u00BF)(\u00B0)(U\u0308)(u\u0308)]", "");

            // Regresar a la forma compuesta, para poder comparar la ñ con la tabla de valores
            quita = Normalizer.normalize(quita, Normalizer.Form.NFC);
        }
        return quita;
    }
    
    //carga el lemario 
    public void cargaFicheroLemario()
    {
     File fichero = new File("src/codigo/lemario-20101017.txt");
        try 
        {
            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr) ;
            String linea;
            while((linea = br.readLine()) != null)
            {
               // System.out.println(linea);
                linea = quitaMierda(linea);
               // linea.(linea,linea);
            }
        } catch (FileNotFoundException ex) 
        {
            Logger.getLogger(LeMario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) 
        {
            Logger.getLogger(LeMario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //comprueba si la palabra está en el lemario
     public boolean esta(String palabra)
     {
	 if(lemario.containsKey(palabra))
        {
            return true;
        }
        return false;
    }
    
   //metodo escalera de palabras 
    
     public boolean compruebaEscalera(char[][] lista)
    {
        
        int contador = 0;

        for (int y = 1; y < lista.length; y++) 
        {
            for (int x = 0; x < lista[y].length; x++) 
            {
                if (lista[y][x] != lista[y - 1][x]) 
                {
                    contador++;
                }
            }
            if (contador != 1)
            {
                return false;
            }
            contador = 0;
        }
        return true;
        
    } 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        new LeMario().cargaFicheroLemario();
    }

  
    
}
