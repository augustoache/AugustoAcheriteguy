/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import pojos.Equipos;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import testhiber.HibernateUtil;

/**
 *
 * @author augus
 */
public class Controller extends Template{
    public Controller(){
        
    }
    
    public static void agregarEquipos(Equipos equi){
        Session s= HibernateUtil.getSessionFactory().openSession();
        Transaction tr= s.beginTransaction();
        
        s.save(equi);
        tr.commit();
        s.close();
        tr=null;
        System.out.println("Grabado exitoso");
    }
    
    public static void eliminarEquipos(Equipos fila){
        
        Transaction t=null;
        Session s= HibernateUtil.getSessionFactory().openSession();
        t=s.beginTransaction();
        Query q=s.createQuery("DELETE Equipos WHERE id="+fila.getId());
        int nroObj=q.executeUpdate();
        System.out.println(nroObj+" Equipos eliminados");
        t.commit();
        
    }
    public static void cargarPartidos(Equipos Local,Equipos Visita,int res){
         
        
        if (res==0){
            Session s= HibernateUtil.getSessionFactory().openSession();
            Transaction tr= s.beginTransaction(); 
            pojos.Equipos eq1= (pojos.Equipos) s.load(pojos.Equipos.class, Local.getId());
            eq1.setPuntos(Local.getPuntos() +3);
            s.update(eq1);        
            tr.commit();        
            s.close();        
            tr=null;
                        
        }else
            if(res==2){
                Session s= HibernateUtil.getSessionFactory().openSession();
                Transaction tr= s.beginTransaction();
                pojos.Equipos eq1= (pojos.Equipos) s.load(pojos.Equipos.class, Visita.getId());
                eq1.setPuntos(Local.getPuntos() +3);
                s.update(eq1);        
                tr.commit();        
                s.close();        
                tr=null;
                                
            }else{
                      
                
            }
         System.out.println("Actualizacion exitosa");

    }
    
    
    public List<Equipos> obtenerEquipos(){
        Session s= HibernateUtil.getSessionFactory().openSession();
        Criteria c = s.createCriteria(pojos.Equipos.class);
        List<pojos.Equipos> equipos= c.list();
        
        return equipos;
    }
    
}
