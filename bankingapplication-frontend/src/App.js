// import logo from './logo.svg';
import './App.css';
import Navigation from './Component/Navigation';
import {
  BrowserRouter as Router
} from "react-router-dom";


function App() {
  return (
    <div className="App">
    <Router>

      <Navigation/>
    </Router>

  </div>
  );
}

export default App;
