package InterfazGrafica;

import Modelo.Universidad;
import Excepciones.ExcepcionCodNoEncontrado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame{
    private Universidad universidad;
    private JTextField textCodConsultaReservador;
    private JTextField textCodAulaCancelar;
    private JTextField textCodReservaCancelar;
    private JTextField textCodAulaReservar;
    private JTextField textCodReservador;
    private JTextField textFecha;
    private JTextField textHoraI;
    private JTextField textHoraF;
    private JTextField textNomOrg;
    private JTextField textCosto;
    private JTextArea textArea;


    public VentanaPrincipal(Universidad uni)
    {
        universidad = uni;
        inicializarComponentes();
    }

    private void inicializarComponentes()
    {
        // Configuración de la ventana
        setTitle("Gestión de Reservas");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Título
        JLabel labelTitulo = new JLabel("GESTIÓN DE RESERVAS", SwingConstants.CENTER);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(labelTitulo, BorderLayout.NORTH);

        // Área de texto para mensajes
        textArea = new JTextArea(5, 20);
        textArea.setEditable(false); //no editable para que solo se use para mensajes
        add(textArea, BorderLayout.SOUTH);
        textArea.setText("Área de texto para mensajes");

        // Panel Central con formularios
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));

        //barra de scroll
        panelCentral.setPreferredSize(new Dimension(1000,1000));
        JScrollPane scrollPane= new JScrollPane();
        scrollPane.setBounds(5,10,1000,1000);
        scrollPane.setViewportView(panelCentral);

        // Formulario de consultar aulas
        panelCentral.add(new JLabel("-Consultar datos de aula/s:"));
        panelCentral.add(new JLabel("   -Por piso:"));
        // Botón de consultar por planta baja
        JButton botonConsultarPB = new JButton("Planta baja");
       /* botonConsultarPB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                funcion()
            }
        });*/
        panelCentral.add(botonConsultarPB);
        // Botón de consultar por piso 1
        JButton botonConsultarP1 = new JButton("Piso 1");
       /* botonConsultarP1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                funcion()
            }
        });*/
        panelCentral.add(botonConsultarP1);
        // Botón de consultar por piso 2
        JButton botonConsultarP2 = new JButton("Piso 2");
       /* botonConsultarP2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                funcion()
            }
        });*/
        panelCentral.add(botonConsultarP2);
        // Botón de consultar por piso 3
        JButton botonConsultarP3 = new JButton("Piso 3");
       /* botonConsultarP3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                funcion()
            }
        });*/
        panelCentral.add(botonConsultarP3);
        panelCentral.add(new JLabel("   -Por código de reservador:"));
        textCodConsultaReservador = new JTextField(10);
        panelCentral.add(textCodConsultaReservador);
        // Botón de consultar por reservador
        JButton botonConsultarReservador = new JButton("Consultar por reservador");
       /* botonConsultarReservador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                funcion()
            }
        });*/
        panelCentral.add(botonConsultarReservador);

        panelCentral.add(new JLabel("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"));

        // Formulario de agregar reserva
        panelCentral.add(new JLabel("-Reservar aula:"));
        panelCentral.add(new JLabel("   -Cod de Aula para reservar:"));
        textCodAulaReservar = new JTextField(10);
        panelCentral.add(textCodAulaReservar);
        panelCentral.add(new JLabel("   -Cod de Asignatura/Curso/Evento para reservar:"));
        textCodReservador = new JTextField(10);
        panelCentral.add(textCodReservador);
        panelCentral.add(new JLabel("       -Si es curso, ingrese fecha de inicio (aaaa-mm-dd):"));
        textFecha = new JTextField(10);
        panelCentral.add(textFecha);
        panelCentral.add(new JLabel("       -Si es curso, ingrese horario de inicio (hh:mm):"));
        textHoraI = new JTextField(10);
        panelCentral.add(textHoraI);
        panelCentral.add(new JLabel("       -Si es curso, ingrese horario de finalización (hh:mm):"));
        textHoraF = new JTextField(10);
        panelCentral.add(textHoraF);
        panelCentral.add(new JLabel("       -Si es evento externo, ingrese nombre de la organización:"));
        textNomOrg = new JTextField(10);
        panelCentral.add(textNomOrg);
        panelCentral.add(new JLabel("       -Si es evento externo, ingrese costo del alquiler:"));
        textCosto = new JTextField(10);
        panelCentral.add(textCosto);
        // Botón de agregar reserva para asignatura
        JButton botonReservarAsig = new JButton("Reservar aula para asignatura");
       /* botonReservarAsig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarReservaAsig()
            }
        });*/
        panelCentral.add(botonReservarAsig);
        // Botón de agregar reserva para curso
        JButton botonReservarCurso = new JButton("Reservar aula para curso");
       /* botonReservarCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarReservaCurso()
            }
        });*/
        panelCentral.add(botonReservarCurso);
        // Botón de agregar reserva para evento interno
        JButton botonReservarEventI = new JButton("Reservar aula para evento interno");
       /* botonReservarEventI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarReservaEventoI()
            }
        });*/
        panelCentral.add(botonReservarEventI);
        // Botón de agregar reserva para evento externo
        JButton botonReservarEventE = new JButton("Reservar aula para evento externo");
       /* botonReservarEventE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarReservaEventoE()
            }
        });*/
        panelCentral.add(botonReservarEventE);

        panelCentral.add(new JLabel("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"));

        // Formulario de cancelar reserva
        panelCentral.add(new JLabel("-Cancelar Reserva:"));
        panelCentral.add(new JLabel("   -Cod de Aula:"));
        textCodAulaCancelar = new JTextField(10);
        panelCentral.add(textCodAulaCancelar);
        panelCentral.add(new JLabel("   -Cod de Reserva:"));
        textCodReservaCancelar = new JTextField(10);
        panelCentral.add(textCodReservaCancelar);
        // Botón de cancelar reserva
        JButton botonCancelarReserva = new JButton("Cancelar Reserva");
        botonCancelarReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                funcionCancelarReserva();
            }
        });
        panelCentral.add(botonCancelarReserva);

        panelCentral.add(new JLabel("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"));

        // Formulario de consultar monto
        panelCentral.add(new JLabel("-Consultar monto recaudado por:"));
        // Botón de consultar monto por aula
        JButton botonMontoAula = new JButton("Aula");
       /* botonMontoAula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                funcion()
            }
        });*/
        panelCentral.add(botonMontoAula);
        // Botón de consultar monto por piso
        JButton botonMontoPiso = new JButton("Piso");
       /* botonMontoPiso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                funcion()
            }
        });*/
        panelCentral.add(botonMontoPiso);
        // Botón de consultar monto por total institución
        JButton botonMontoTotal = new JButton("Institución (total)");
       /* botonMontoTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                funcion()
            }
        });*/
        panelCentral.add(botonMontoTotal);

        panelCentral.add(new JLabel("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"));
        // Botón de consultar cantidad de reservas por aula (y promedio)
        JButton botonCantRes = new JButton("Consultar cantidad de reservas por aula (y promedio por aula)");
       /* botonCantRes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                funcion()
            }
        });*/
        panelCentral.add(botonCantRes);

        //agregar lo del panel central con scroll
        add(scrollPane, BorderLayout.CENTER);
    }

    private void funcionCancelarReserva() {
        try {
            int codAula = Integer.parseInt(textCodAulaCancelar.getText());
            int codReserva = Integer.parseInt(textCodReservaCancelar.getText());
            universidad.cancelarReservaAula(codAula, codReserva);
            textArea.setText("Se canceló la reserva " + codReserva + " en el aula " + codAula + " exitosamente.");
        } catch (ExcepcionCodNoEncontrado e) {
            textArea.setText(e.getMessage());
        }catch (NumberFormatException e) {
            textArea.setText("Error: Código de Aula o Código de Reserva no válidos.");
        }
    }

    /*
    private void agregarReservaAsig() {
        try {
            int codAula = Integer.parseInt(textCodAulaCancelar.getText());
            int codReserva = Integer.parseInt(textCodReservaCancelar.getText());
            universidad.cancelarReservaAula(codAula, codReserva);
            textArea.append("Se canceló la reserva " + codReserva + " en el aula " + codAula + " exitosamente.\n");
            JOptionPane.showMessageDialog(this, "Reserva cancelada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (ExcepcionCodNoEncontrado e) {
            textArea.append(e.getMessage() + "\n");
        }
    }

    private void agregarReservaCurso() {
        try {
            int codAula = Integer.parseInt(textCodAulaCancelar.getText());
            int codReserva = Integer.parseInt(textCodReservaCancelar.getText());
            universidad.cancelarReservaAula(codAula, codReserva);
            textArea.append("Se canceló la reserva " + codReserva + " en el aula " + codAula + " exitosamente.\n");
            JOptionPane.showMessageDialog(this, "Reserva cancelada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (ExcepcionCodNoEncontrado e) {
            textArea.append(e.getMessage() + "\n");
        }
    }

    private void agregarReservaEventoI() {
        try {
            int codAula = Integer.parseInt(textCodAulaCancelar.getText());
            int codReserva = Integer.parseInt(textCodReservaCancelar.getText());
            universidad.cancelarReservaAula(codAula, codReserva);
            textArea.append("Se canceló la reserva " + codReserva + " en el aula " + codAula + " exitosamente.\n");
            JOptionPane.showMessageDialog(this, "Reserva cancelada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (ExcepcionCodNoEncontrado e) {
            textArea.append(e.getMessage() + "\n");
        }
    }

    private void agregarReservaEventoE) {
        try {
            int codAula = Integer.parseInt(textCodAulaCancelar.getText());
            int codReserva = Integer.parseInt(textCodReservaCancelar.getText());
            universidad.cancelarReservaAula(codAula, codReserva);
            textArea.append("Se canceló la reserva " + codReserva + " en el aula " + codAula + " exitosamente.\n");
            JOptionPane.showMessageDialog(this, "Reserva cancelada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (ExcepcionCodNoEncontrado e) {
            textArea.append(e.getMessage() + "\n");
        }
    }


     */


}

