
package Report;

import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


public class GerarRelatorio {
     private String relatorio;
    private String arg;
    private final Connection connection;
    
    //estaciando a variavel de conexão do banco
    public GerarRelatorio() {
        this.connection = new ConnectionFactory().getConnection();
    }
    
    // metodo ficha 
    public void ficha(String relatorio, String arg) throws JRException, Exception {
        
        this.relatorio = relatorio;
        this.arg = arg;
        
     
        // metodo Statement É usado para criar um objeto que representa a instrução SQL que será executada, sendo que é invocado através do objeto Connetion.
        Statement stm =  connection.createStatement();
        String query;
        
        query = "select * from usuario where id='"+ arg + "'";
        
        ResultSet rs = stm.executeQuery(query);
        
        JRResultSetDataSource jrRS = new JRResultSetDataSource( rs );
        
        Map parameters = new HashMap();
        
        JasperPrint jp = JasperFillManager.fillReport(relatorio, parameters, jrRS);
        
        JasperViewer.viewReport(jp,false);
        
    }
}
