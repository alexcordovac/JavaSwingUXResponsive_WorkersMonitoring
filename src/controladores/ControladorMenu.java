/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import formularios.FormPrincipal;
import formularios.FormRecordObtenidos;
import formularios.FormTrabajador;
import formularios.MenuItem;
import formularios.PanelTrabajadorLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import utiles.Constantes;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import jiconfont.swing.IconFontSwing;
import recursos.iconos.GoogleMaterialDesignIcons;

/**
 *
 * @author Alex
 */
public class ControladorMenu {

    private FormPrincipal vista;
    private HashMap<MenuItem, JPanel> menusVistas;
    private MenuItem menuSeleccionado;

    public ControladorMenu(FormPrincipal vista) {
        this.vista = vista;
        iniMenus();
    }

    /*Función para crear y pintar los menus en el panelMenus de la vista principal*/
    private void iniMenus() {
        menusVistas = new HashMap<>();
        
        //Menu items
        MenuItem asignarTrabajo = new MenuItem(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.ASSIGNMENT_IND, 30, Constantes.COLOR_PRIMARIO), "Asignar trabajo");
        MenuItem recordsObtenidos = new MenuItem(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.EQUALIZER, 30, Constantes.COLOR_PRIMARIO), "Records obtenidos");
        
        //Los guardamos en el hashmap con su respectivo formulario
        menusVistas.put(asignarTrabajo, new PanelTrabajadorLayout());
        menusVistas.put(recordsObtenidos , new FormRecordObtenidos());
        
        //Los pintamos en la vista
        pintarMenuItems(asignarTrabajo, recordsObtenidos);
    }
    
    private void pintarMenuItems(MenuItem... menu) {
        JPanel panelMenus = vista.getPanelMenus();
        MouseListener mouseListener = iniMouseListener();
        for (int i = 0; i < menu.length; i++) {
            panelMenus.add(menu[i]);
            menu[i].addMouseListener(mouseListener);
        }
        panelMenus.revalidate();
        panelMenus.repaint();
    }

    private MouseListener iniMouseListener() {
        MouseListener mouseListenerPanel = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controlarVista(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };

        return mouseListenerPanel;
    }

    private void controlarVista(MouseEvent e) {
        MenuItem tmp = (MenuItem) e.getSource();

        if (tmp != this.menuSeleccionado) {

            if (this.menuSeleccionado != null) {
                this.menuSeleccionado.resetColor();
            }

            tmp.setColor();
            this.menuSeleccionado = tmp;

            //Si el menu ya está registrado en el hashmap con su JPanel lo traemos y lo pintamos
            JPanel hashFound = this.menusVistas.get(tmp);
            if (hashFound != null) {
                JPanel panel = vista.getPanelCuerpo();
                panel.removeAll();
                panel.add(hashFound);
                panel.repaint();
                panel.revalidate();
            }
        }
    }

}