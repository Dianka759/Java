<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="ISO-8859-1">
        <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
        <title>Submit Omikuji Fortune</title>
    </head>

    <body class="bg-dark text-light">
        <div class="mt-5">
        <h3 class="text-center">Send an Omikuji!</h3>
        <div class="container p-4 border border-danger">
            <form action="/submitFortune" method="post">
                <div class="mb-3">
                    <label class="form-label">Pick any number from 5 to 25:</label>
                    <input class="form-control" type="number" name="number" min="5" max="25">
                </div>
                <div class="mb-3">
                    <label class="form-label">Enter the name of any city:</label>
                    <input class="form-control" type="text" name="city">
                </div>
                <div class="mb-3">
                <label class="form-label">Enter the name of any real person:</label>
                <input class="form-control" type="text" name="person">
                </div>
                <div class="mb-3">
                    <label class="form-label">Enter professional endeavor or hobby:</label>
                    <input class="form-control" type="text" name="hobby">
                </div>
                <div class="mb-3">
                    <label class="form-label">Enter any type of living thing:</label>
                    <input class="form-control" type="text" name="livingThing">
                </div>
                <div class="mb-3">
                    <label class="form-label">Say something nice to someone:</label>
                    <textarea class="form-control" name="somethingNice"></textarea>
                </div>
                <div class="mb-3">
                <p>Send and show a friend!</p>
                </div>
                <div class="text-center">
                    <input class="btn btn-danger" type="submit" value="Send!">
                </div>
            </form>
        </div>
        </div>
    </body>

    </html>