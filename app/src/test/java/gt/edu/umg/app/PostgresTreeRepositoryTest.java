package gt.edu.umg.app;

import gt.edu.umg.app.model.TreeNode;
import gt.edu.umg.app.repository.PostgresTreeRepository;
import gt.edu.umg.app.repository.SpringDataTreeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostgresTreeRepositoryTest {

    @Mock
    private SpringDataTreeRepository jpaRepository;

    @InjectMocks
    private PostgresTreeRepository repository;

    private List<TreeNode> nodosPrueba;

    @BeforeEach
    void setUp() {
        TreeNode raiz = new TreeNode("1", "Tienda", null);
        TreeNode electronica = new TreeNode("2", "Electronica", "1");
        TreeNode computadoras = new TreeNode("4", "Computadoras", "2");
        TreeNode laptops = new TreeNode("6", "Laptops", "4");

        nodosPrueba = Arrays.asList(raiz, electronica, computadoras, laptops);
    }

    @Test
    void testCrearRaiz() {
        repository.createRoot("1", "Tienda");
        verify(jpaRepository, times(1)).save(any(TreeNode.class));
    }

    @Test
    void testAgregarHijo() {
        repository.addChild("1", "2", "Electronica");
        verify(jpaRepository, times(1)).save(any(TreeNode.class));
    }

    @Test
    void testObtenerArbolCompleto() {
        when(jpaRepository.findAll()).thenReturn(nodosPrueba);
        List<TreeNode> resultado = repository.getFullTree();
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals("Tienda", resultado.get(0).getValue());
    }

    @Test
    void testProfundidadNodo() {
        when(jpaRepository.findAll()).thenReturn(nodosPrueba);
        int profundidad = repository.getDepth("6");
        assertEquals(3, profundidad);
    }

    @Test
    void testRutaDesdeRaiz() {
        when(jpaRepository.findAll()).thenReturn(nodosPrueba);
        List<String> ruta = repository.getPathFromRoot("6");
        assertEquals("Tienda", ruta.get(0));
        assertEquals("Laptops", ruta.get(ruta.size() - 1));
    }

    @Test
    void testAncestrosNodo() {
        when(jpaRepository.findAll()).thenReturn(nodosPrueba);
        List<TreeNode> ancestros = repository.getAncestors("6");
        assertFalse(ancestros.isEmpty());
        assertEquals("Computadoras", ancestros.get(0).getValue());
    }

    @Test
    void testAlturaArbol() {
        when(jpaRepository.findAll()).thenReturn(nodosPrueba);
        int altura = repository.getHeight();
        assertEquals(3, altura);
    }

    @Test
    void testValidarSinCiclos() {
        when(jpaRepository.findAll()).thenReturn(nodosPrueba);
        boolean valido = repository.validateNoCycles();
        assertTrue(valido);
    }

    @Test
    void testRecorridoDFS() {
        when(jpaRepository.findAll()).thenReturn(nodosPrueba);
        List<TreeNode> dfs = repository.getDFS();
        assertNotNull(dfs);
        assertFalse(dfs.isEmpty());
        assertEquals("Tienda", dfs.get(0).getValue());
    }

    @Test
    void testRecorridoBFS() {
        when(jpaRepository.findAll()).thenReturn(nodosPrueba);
        List<TreeNode> bfs = repository.getBFS();
        assertNotNull(bfs);
        assertFalse(bfs.isEmpty());
        assertEquals("Tienda", bfs.get(0).getValue());
    }

    @Test
    void testSubarbol() {
        when(jpaRepository.findAll()).thenReturn(nodosPrueba);
        TreeNode subarbol = repository.getSubtree("2");
        assertNotNull(subarbol);
        assertEquals("Electronica", subarbol.getValue());
    }
}