<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.nio.charset.Charset" %>
<html lang="ka">
<style>
    p.medium {
        line-height: 1.5
    }
</style>

<body>
<form accept-charset="UTF-8">
    <p class="medium">
        <label for="fullName"> გამოცდის სახელი: </label>
        <input type="text" id="fullName" name="fullName" accept-charset="UTF-8"></input><br>

        <label for="startDate"> დაწყების დრო: </label>
        <input type="datetime-local" id="startDate" name="startDate"></input><br>

        <label for="duration_hr"> ხანგრძლივობა: </label>
        <input type="number" id="duration_hr" name="hours" style="width: 40px;" min="0" max="9"></input>
        <label> სთ. </label>
        <input type="number" id="duration_mn" name="minutes" style="width: 40px;" min="0" max="60"></input>
        <label> წთ. </label><br>

        <label for="variants"> ვარიანტების რაოდენობა: </label>
        <input type="number" id="variants" name="variants" min="1" max="10" value="1"></input>

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
                    label.for = "statement " + i;
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
</body>
</html>