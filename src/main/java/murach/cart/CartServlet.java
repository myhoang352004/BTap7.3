package murach.cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import murach.business.Cart;
import murach.business.Item;

import java.io.IOException;

public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String url="/index.jsp";
        HttpSession session = req.getSession();
        String action=req.getParameter("productCode");

        if(action ==null){
            action="join";
        }
        if(action.equals("join")){
            url="/index.jsp";
        }
        else
        {
            if(action.equals("add")){
                String id=req.getParameter("id");
                String name=req.getParameter("name");
                String price=req.getParameter("price");
                Item item=new Item(id,name,Double.parseDouble(price));
                Cart cart = (Cart) session.getAttribute("cart");
                if(cart==null){
                    cart=new Cart();
                }

                cart.addProduct(item);
                session.setAttribute("cart",cart);
                url="/cart.jsp";
            }
            else if(action.equals("checkout")){
                url="/checkout.jsp";
            }
            else if(action.equals("remove")){
                String id=req.getParameter("id");
                Cart cart = (Cart) session.getAttribute("cart");
                cart.removeProduct(id);
                url="/cart.jsp";
            }
            else if (action.equals("update")) {
                String id = req.getParameter("id");
                String quantity = req.getParameter("quantity");
                int quantityInt = Integer.parseInt(quantity);
                Cart cart = (Cart)session.getAttribute("cart");
                if (quantityInt != 0) {
                    cart.UpdateProduct(id, quantityInt);
                } else if (quantityInt == 0) {
                    cart.removeProduct(id);
                }

                url = "/cart.jsp";
            }
        }
        getServletContext()
                .getRequestDispatcher(url).forward(req,resp);

    }
}
