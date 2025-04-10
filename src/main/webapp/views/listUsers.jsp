<%@page import = "java.util.List, dao.UserDAO, model.User"%>

<div id="container-table">
    <table>
        <thead>
            <tr>
                <th id="th1">id</th>
                <th>Nombres</th>
                <th>Apellidos</th>
                <th>Email</th>
                <th>Username</th>
                <th id="th6">Password</th>
            </tr>
        </thead>
        <tbody>
            <%
            UserDAO userDao = new UserDAO();
            List<User> userList = userDao.userList(session);
            for(User user: userList) {
            %>
            <tr>
                <td><%=user.getId()%></td>
                <td><%=user.getName()%></td>
                <td><%=user.getLastname()%></td>
                <td><%=user.getEmail()%></td>
                <td><%=user.getUsername()%></td>
                <td><%=user.getPassword()%></td>
            </tr>
            <% } %>
        </tbody>
    </table>
</div>