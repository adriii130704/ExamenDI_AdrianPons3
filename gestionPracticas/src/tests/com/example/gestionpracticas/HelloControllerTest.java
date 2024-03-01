package com.example.gestionpracticas;
import com.example.modelos.Alumno;
import com.example.modelos.Empresa;
import com.example.modelos.Practicas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloControllerTest extends ApplicationTest {

    private HelloController helloController;

    @Override
    public void start(Stage stage) {
        // Inicializa el controlador (puedes ajustar esto según tu implementación)
        helloController = new HelloController();
        stage.show();
    }

    @Test
    public void testAnyadirEmpresaLista() {
        // Simula los valores necesarios para la prueba
        helloController.setIdDarAltaEmpresa(new TextField("1"));
        helloController.setNombreDarAltaEmpresa(new TextField("Empresa1"));
        helloController.setResponsableDarAltaEmpresa(new TextField("Responsable1"));
        helloController.setDireccionDarAltaEmpresa(new TextField("Dirección1"));

        // Ejecuta el método
        helloController.anyadirEmpresaLista(new ActionEvent());

        // Verifica que la lista de empresas se haya actualizado
        ObservableList<Empresa> empresas = helloController.getEmpresas().getItems();
        assertEquals(1, empresas.size());
        assertEquals("Empresa1", empresas.get(0).getNombre());
    }

    @Test
    public void testAnyadirAlumnos() {
        // Simula los valores necesarios para la prueba
        helloController.setDniDarAltaAlumno(new TextField("123456"));
        helloController.setNombreDarAltaAlumno(new TextField("Estudiante1"));
        helloController.setTelefonoAltaAlumno(new TextField("123"));

        // Ejecuta el método
        helloController.anyadirAlumnos(new ActionEvent());

        // Verifica que la lista de alumnos se haya actualizado
        ObservableList<Alumno> alumnos = helloController.getAlumnos().getItems();
        assertEquals(1, alumnos.size());
        assertEquals("Estudiante1", alumnos.get(0).getNombre());
    }

    @Test
    public void testAsignarPracticas() {
        // Simula los valores necesarios para la prueba
        // (asegúrate de que hay elementos seleccionados en las listas antes de ejecutar la prueba)
        helloController.getEmpresas().getItems().add(new Empresa(1, "Empresa1", "Responsable1", "Dirección1"));
        helloController.getAlumnos().getItems().add(new Alumno("123456", "Estudiante1", 123, false));
        helloController.getEmpresas().getSelectionModel().select(0);
        helloController.getAlumnos().getSelectionModel().select(0);
        helloController.setFechaInicio(new DatePicker(LocalDate.now()));
        helloController.setFechaFinal(new DatePicker(LocalDate.now().plusDays(7)));

        // Ejecuta el método
        helloController.asignarPracticas(new ActionEvent());

        // Verifica que la lista de prácticas se haya actualizado
        ObservableList<Practicas> practicas = helloController.getPracticas().getItems();
        assertEquals(1, practicas.size());
        assertEquals("Empresa1", practicas.get(0).getEmpresa().getNombre());
        assertEquals("Estudiante1", practicas.get(0).getAlumno().getNombre());
    }

    @Test
    public void testDelete() {
        // Simula la selección de un elemento en la lista de prácticas
        ObservableList<Practicas> practicas = FXCollections.observableArrayList(new Practicas(1,
                new Empresa(1, "Empresa1", "Responsable1", "Dirección1"),
                new Alumno("123456", "Estudiante1", 123, false),
                LocalDate.now(), LocalDate.now().plusDays(7), "Responsable1"));
        helloController.getPracticas().setItems(practicas);
        helloController.getPracticas().getSelectionModel().select(0);

        // Ejecuta el método
        helloController.delete(new ActionEvent());

        // Verifica que la lista de prácticas se haya actualizado
        assertEquals(0, helloController.getPracticas().getItems().size());
    }
}