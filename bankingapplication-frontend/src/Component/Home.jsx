import React from 'react';


function Home() {
    return(
        <div className="Login-Form">
            <form>
                <label for="fname">First name:</label><br>
                <input type="text" id="fname" name="fname"/></br>
                <label for="lname">Last name:</label><br>
                <input type="text" id="lname" name="lname"/></br>
            </form>
         </div>
    )
}

export default Home;