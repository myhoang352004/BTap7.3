package murach.email;

import java.io.*;
import jakarta.mail.MessagingException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import murach.business.User;
import murach.data.UserDB;
import murach.util.*;
import murach.business.Cart;
import murach.business.Total;

public class EmailListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        double totalPrice = cart.getTotalPrice();

    // Gửi tổng tiền vào request để sử dụng trong JSP hoặc email
        request.setAttribute("totalPrice", totalPrice);
        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "join";  // default action
        }
        // perform action and set URL to appropriate page
        String url = "/dangnhap.jsp";
        if (action.equals("join")) {
            url = "/dangnhap.jsp";    // the "join" page
        } else if (action.equals("add")) {
            // get parameters from the request
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");

            // store data in User object
            User user = new User(email, firstName, lastName );
            UserDB.insert(user);
            request.setAttribute("user", user);
            
//            String cssPath = getServletContext().getRealPath("/css/main.css");
//            String cssContent = getCssContent(cssPath);
             // send email to user
            String to = email;
            String from = "email_list@murach.com";
            String subject = "Welcome to our email list";
            StringBuilder emailBody = new StringBuilder();
            emailBody.append("<html><body>");
            emailBody.append("<table border='0' cellspacing='0' cellpadding='15' style='border: 1px solid #ddd; border-collapse: collapse; width: 100%; font-family: Arial, sans-serif;'>");

            // Header: Lời chào và màu sắc nổi bật
            emailBody.append("<tr>");
            emailBody.append("<td colspan='3' style='text-align: left; font-weight: bold; font-size: 16px; background-color: #4BA1F0; color: white;'>");
            emailBody.append("Dear ").append(firstName).append(",");
            emailBody.append("</td>");
            emailBody.append("</tr>");

            // Dòng trống
            emailBody.append("<tr>");
            emailBody.append("<td colspan='3'>&nbsp;</td>");
            emailBody.append("</tr>");

            // Lời cảm ơn
            emailBody.append("<tr>");
            emailBody.append("<td colspan='3' style='font-size: 14px; color: #555;'>");
            emailBody.append("Thank you for your order! Below are the details:");
            emailBody.append("</td>");
            emailBody.append("</tr>");

            // Kiểm tra và hiển thị thông tin giỏ hàng
            if (cart != null && !cart.getTotals().isEmpty()) {
                // Tiêu đề bảng
                emailBody.append("<tr style='background-color: #f9f9f9; border-bottom: 2px solid #ddd;'>");
                emailBody.append("<th style='text-align: left; padding: 10px; border-bottom: 1px solid #ddd;'>Product Name</th>");
                emailBody.append("<th style='text-align: left; padding: 10px; border-bottom: 1px solid #ddd;'>Quantity</th>");
                emailBody.append("<th style='text-align: left; padding: 10px; border-bottom: 1px solid #ddd;'>Amount</th>");
                emailBody.append("</tr>");

                // Duyệt qua sản phẩm trong giỏ hàng
                for (Total totalItem : cart.getTotals()) {
                    emailBody.append("<tr>");
                    emailBody.append("<td style='padding: 10px; border-bottom: 1px solid #ddd;'>")
                            .append(totalItem.getItem().getName()).append("</td>");
                    emailBody.append("<td style='padding: 10px; border-bottom: 1px solid #ddd;'>")
                            .append(totalItem.getQuantity()).append("</td>");
                    emailBody.append("<td style='padding: 10px; border-bottom: 1px solid #ddd;'>$")
                            .append(totalItem.finalPrice()).append("</td>");
                    emailBody.append("</tr>");
                }

                // Hàng tổng tiền
                emailBody.append("<tr style='background-color: #f1f1f1;'>");
                emailBody.append("<td colspan='2' style='text-align: right; font-weight: bold; padding: 10px;'>Total Price:</td>");
                emailBody.append("<td style='padding: 10px;'><strong>$").append(cart.getTotalPrice()).append("</strong></td>");
                emailBody.append("</tr>");
            } else {
                // Thông báo giỏ hàng trống
                emailBody.append("<tr>");
                emailBody.append("<td colspan='3' style='text-align: center; padding: 15px; font-size: 14px; color: #888;'>");
                emailBody.append("Your cart is empty.");
                emailBody.append("</td>");
                emailBody.append("</tr>");
            }

            // Dòng trống
            emailBody.append("<tr>");
            emailBody.append("<td colspan='3'>&nbsp;</td>");
            emailBody.append("</tr>");

            // Footer: Lời cảm ơn cuối email
            emailBody.append("<tr>");
            emailBody.append("<td colspan='3' style='background-color: #4BA1F0; color: white; padding: 15px; text-align: left;'>");
            emailBody.append("Thank you for shopping with us!<br>");
            emailBody.append("Best regards,<br>");
            emailBody.append("Nguyen Le My Hoang");
            emailBody.append("</td>");
            emailBody.append("</tr>");

            emailBody.append("</table>");
            emailBody.append("</body></html>");


            boolean isBodyHTML = true;
            try {
                MailUtilLocal.sendMail(to, from, subject, emailBody.toString(), 
                                       isBodyHTML);
                            } catch (MessagingException e) {
                String errorMessage
                    = "ERROR: Unable to send email. "
                    + "Check Tomcat logs for details.<br>"
                    + "NOTE: You may need to configure your system "
                    + "as described in chapter 14.<br>"
                    + "ERROR MESSAGE: " + e.getMessage();
                request.setAttribute("errorMessage", errorMessage);
                    this.log(
                    "Unable to send email. \n"
                    + "Here is the email you tried to send: \n"
                    + "=====================================\n"
                    + "TO: " + email + "\n"
                    + "FROM: " + from + "\n"
                    + "SUBJECT: " + subject + "\n\n"
                    + emailBody.toString() + "\n\n");
            }
            url = "/checkout.jsp";
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
}