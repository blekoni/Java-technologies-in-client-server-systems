package first;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import first.CurrencyWSStub.GetCoeficient;


public class CurrencyServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public CurrencyServlet() {
		super();
    }

    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
    	dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
    	CurrencyWSStub stub = new CurrencyWSStub("http://localhost:8080/CurrencyService/services/CurrencyWS?wsdl");
    	GetCoeficient gc = new GetCoeficient();
    	
    	String currency1 = request.getParameter("currency1");
    	String currency2 = request.getParameter("currency2");
    	
    	gc.setCurrency1(currency1);
    	gc.setCurrency2(currency2);
    	String[] currencyBank = stub.getCoeficient(gc).get_return();
    	request.setAttribute("banksInfo",Arrays.asList(currencyBank));
    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
    	dispatcher.forward(request, response);
    }
}
