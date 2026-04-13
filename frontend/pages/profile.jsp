<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>Profile</title>

<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet">
<link rel="stylesheet" href="../css/profile.css">
</head>

<body>

<div class="profile-container">

    <!-- LEFT SIDE -->
    <div class="profile-left">

        <div class="profile-pic">
            <img 
                src="<%= request.getAttribute("profile_image") != null ? request.getAttribute("profile_image") : "../assets/default.png" %>" 
                id="preview">
        </div>

        <h2 class="profile-name">
            <%= request.getAttribute("name") %>
        </h2>

        <!-- Upload -->
        <form action="/updateProfile" method="post" enctype="multipart/form-data" class="upload-form">
            <input type="file" name="profile_image" required>
            <button type="submit">Upload</button>
        </form>

        <!-- Remove -->
        <form action="/removeImage" method="post">
            <button type="submit" class="remove-btn">Remove</button>
        </form>

    </div>

    <!-- RIGHT SIDE -->
    <div class="profile-right">

        <div class="info-card">

            <div class="info-row">
                <span class="label">Age</span>
                <span class="value"><%= request.getAttribute("age") %></span>
            </div>

            <div class="info-row">
                <span class="label">College</span>
                <span class="value"><%= request.getAttribute("college") %></span>
            </div>

            <div class="info-row">
                <span class="label">Department</span>
                <span class="value"><%= request.getAttribute("department") %></span>
            </div>

            <div class="info-row">
                <span class="label">USN</span>
                <span class="value"><%= request.getAttribute("usn") %></span>
            </div>

            <!-- CGPA -->
            <div class="info-row cgpa-section">
                <span class="label">Current CGPA</span>

                <div class="cgpa-update">
                    <form action="/updateCGPA" method="post">
                        <input type="text" name="cgpa" placeholder="Enter CGPA" required>
                        <button type="submit">Update</button>
                    </form>
                </div>

                <span class="value cgpa-value">
                    <%= request.getAttribute("cgpa") %>
                </span>
            </div>

        </div>

    </div>

</div>

</body>
</html>