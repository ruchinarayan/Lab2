import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TodoServlet
 */
@WebServlet("/TodoServlet")
public class TodoServlet extends HttpServlet {
	
	ConcurrentHashMap<String, String> messageHashMap = null;
	ConcurrentHashMap<String, String> timeHashMap = null;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodoServlet() {
        super();
        // TODO Auto-generated constructor stub
        messageHashMap = new ConcurrentHashMap<String, String>();
        timeHashMap = new ConcurrentHashMap<String, String>();
    }
    
    public void init() throws ServletException
    {
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		response.setContentType("text/html");
		PrintWriter out =  response.getWriter();
		
		String todoReq = request.getParameter("operation");
		//System.out.println(todoReq);
		String[] inputArr = todoReq.split(" ");
		
		StringBuffer inp = new StringBuffer();
		
		for(int i=0;i<inputArr.length;i++)
		{
			inputArr[i] = inputArr[i].replace("[", "");
			inputArr[i] = inputArr[i].replace("]", "");
			if(i>=2)
			{
				inp.append(inputArr[i]);
				inp.append(" ");
			}
		}
		//System.out.println(inputArr[0]+","+inp);
		String title = "TODO Operation";
	    String docType =
	      "<!doctype html public \"-//w3c//dtd html 4.0 " +
	      "transitional//en\">\n";
		
		
		// POST [id] [todo message] : Stores the string 'todo message' in the  database with the supplied integer 'id' and the client's timestamp.
		if((inputArr[0].equals("POST")) && (inputArr.length>=3))
		{
			Date date = new Date();
			String timesave = date.toString();
		    out.println(docType +
	                "<html>\n" +
	                "<head><title>" + title + "</title></head>\n" +
	                "<body bgcolor=\"#f0f0f0\">\n" +
	                "<h1>POST [id] [todo message]</h1>\n" +
	                
	                "  <h2><b>Id: </b> "
	                + inputArr[1] + "</h2>\n" +
	                "  <h2>ToDO Message: </b>"
	                + inp + "</h2>\n" +
	                "  <h2>Time : </b>"
	                + timesave + "</h2>\n" +
	                
	                "<h1>Submitted !!</h1>\n" +
	                "</body></html>");
		    String inpStr = inp.toString();
		    messageHashMap.put(inputArr[1], inpStr);
		    timeHashMap.put(inputArr[1], timesave);
		    
		}
		// GET [id] : Retrieves and displays the todo message to the console and  when it was posted
		else if((inputArr[0].equals("GET")) && (inputArr.length==2))
		{
			  out.println(docType +
		                "<html>\n" +
		                "<head><title>" + title + "</title></head>\n" +
		                "<body bgcolor=\"#f0f0f0\">\n" +
		                "<h1>GET [id]</h1>\n" +
		                "  <h2>Todo Message with id "+inputArr[1]+" is : "
		                + messageHashMap.get(inputArr[1]) + "</h2>\n" +
		                "<h2> saved at: "
		                + timeHashMap.get(inputArr[1]) + "</h2>\n" +
		                "</body></html>");

		}
		// "GET" : Retrieves a List of all todo messages as a map of <id,todo  message> pairs and prints it to the console
		else if((inputArr[0].equals("GET")) && (inputArr.length==1))
		{
			out.println(docType +
	                "<html>\n" +
	                "<head><title>" + title + "</title></head>\n" +
	                "<body bgcolor=\"#f0f0f0\">\n" +
	                "<h1>GET</h1>\n" +
	                "<h1>List of All the TODO messages: </h1>\n" +
	                "</body></html>");
			   for (String key : messageHashMap.keySet()) {
				    out.println("<h2>Id: "+key+", TODO Message: "+messageHashMap.get(key)+", Time saved: "+timeHashMap.get(key)+"</h2>");
				}
		
		}
		// DELETE [id] : Deletes the todo message at the given id from the database
		else if((inputArr[0].equals("DELETE")) && (inputArr.length==2))
		{
			out.println(docType +
	                "<html>\n" +
	                "<head><title>" + title + "</title></head>\n" +
	                "<body bgcolor=\"#f0f0f0\">\n" +
	                "<h1>DELETE [id]</h1>\n" +
	                "  <h2>Todo Message with id "+inputArr[1]+", has been deleted !!</h2>\n" +
	                "</body></html>");
			messageHashMap.remove(inputArr[1]);
			timeHashMap.remove(inputArr[1]);
		}
		else if((inputArr[0].equals("PUT")) && (inputArr.length>=3))
		{
			Date date = new Date();
			String timesave = date.toString();
			   out.println(docType +
		                "<html>\n" +
		                "<head><title>" + title + "</title></head>\n" +
		                "<body bgcolor=\"#f0f0f0\">\n" +
		                "<h1>POST [id] [todo message]</h1>\n" +
		                "  <h2>Id: </b> "
		                + inputArr[1] + "</h2>\n" +
		                "  <h2>ToDO Message: </b>"
		                + inp + "</h2>\n" +
		                "<h1>Value Updated !!</h1>\n" +
		                "</body></html>");
			    String inpStr = inp.toString();
			   
			    if(messageHashMap.get(inputArr[1]) != null)
			    {
			    	 messageHashMap.replace(inputArr[1], inpStr);
					 timeHashMap.replace(inputArr[1], timesave);
			    }
			    else
			    {
			    	 messageHashMap.put(inputArr[1], inpStr);
					 timeHashMap.put(inputArr[1], timesave);
			    }
		}
		else
		{
			System.out.println("Not a Valid Option !!");
			System.out.println();
			out.println(docType +
	                "<html>\n" +
	                "<head><title>" + title + "</title></head>\n" +
	                "<body bgcolor=\"#f0f0f0\">\n" +
	                "<h1>Please enter proper HTTP Crud operation !!</h1>\n" +
	                "</body></html>");
		}
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
   
}
