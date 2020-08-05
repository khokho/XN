<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>


<div class="my-sidebar bg-light border-right" id="sidebar-wrapper" style="position: fixed; z-index: 10; overflow: auto;" >
    <div class="sidebar-heading">weeeee </div>
    <div class="list-group list-group-flush">
        <div id="sidebar-elements">
        </div>
    </div>
</div>

<script type="text/babel">
    class SidebarElements extends React.Component{
        constructor(props) {
            super(props);
            this.state = {elements:[]}
            var sidebarURL = window.location.protocol + "//" + window.location.host + "/getSidebar"
            fetch(sidebarURL)
                .then(resp=>resp.json())
                .then(data=>{
                    this.setState({elements:data})
                })
        }

        render(){
            return (
                <div>
                    {this.state.elements.map(el=>
                        (<a key={el.path} href={el.path} className="list-group-item list-group-item-action bg-light" >{el.name}</a>))
                    }
                </div>
            );
        }
    }

    const inputContainer = document.querySelector('#sidebar-elements');


    ReactDOM.render(<SidebarElements/>, inputContainer);
</script>

    <!-- Menu Toggle Script -->
    <!--<script>

      $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
      });
    </script>
    -->
