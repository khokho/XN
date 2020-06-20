<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.nio.charset.Charset" %>
<html lang="ka">
<style>
    p.medium {
        line-height: 1.5
    }
</style>

<body>
<form accept-charset="UTF-8" enctype="multipart/form-data">
    <p class="medium">
        <label for="fullName"> გამოცდის სახელი: </label>
        <input type="text" id="fullName" name="fullName" required><br>

        <label for="startDate"> დაწყების დრო: </label>
        <input type="datetime-local" id="startDate" name="startDate" required><br>

        <label for="duration_hr"> ხანგრძლივობა: </label>
        <input type="number" id="duration_hr" name="hours" style="width: 40px;" min="0" max="9" required>
        <label> სთ. </label>
        <input type="number" id="duration_mn" name="minutes" style="width: 40px;" min="0" max="60" required>
        <label> წთ. </label><br>

        <label for="variants"> ვარიანტების რაოდენობა: </label>
        <input type="number" id="variants" name="variants" min="1" max="10" value="1" required>

    <div id="statements"></div>


    <script>
        addFiles();
        const inp = document.getElementById("variants");
        inp.addEventListener('change', () => {
            addFiles()
        });

        function addFiles() {
            let container = document.getElementById("statements");
            let newSize = document.getElementById("variants").value;

            let oldSize = (container.childNodes.length);

            if (oldSize < newSize)
                for (let i = oldSize + 1; i <= newSize; i++) {
                    let subContainer = document.createElement("div");
                    let label = document.createElement("label");
                    label.for = "" + i;
                    label.textContent = "ვარიანტი #" + i + ": ";
                    subContainer.appendChild(label);

                    let input = document.createElement("input");
                    input.type = "file";
                    input.id = "statement " + i;
                    input.name = "statement " + i;

                    subContainer.appendChild(input);
                    subContainer.appendChild(document.createElement("br"));
                    container.appendChild(subContainer);
                }
            else {
                while (oldSize > newSize) {
                    container.removeChild(container.lastChild);
                    oldSize--;
                }
            }
        }
    </script>


    <input type="submit" value="შექმნა" formmethod="post"><br>
    <p>
</form>

<c:if test="${not empty resultmessage}">
    <h1>${resultmessage}</h1>
</c:if>
</body>
</html>