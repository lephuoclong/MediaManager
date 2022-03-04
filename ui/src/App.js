/** @format */

import "./GlobalStyles/GlobalStyle.css";
import { Component } from "react";
import { initializeIcons } from "@fluentui/react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import ErrorPage from "./features/ErrorPage";
import LayoutPage from "./layouts/LayoutPage";

class App extends Component {
  constructor(props) {
    super(props);
    initializeIcons();
  }

  render() {
    return (
      <Router>
        <Routes>
          <Route path='/error' element={<ErrorPage />} />
          <Route path='*' element={<LayoutPage />} />
        </Routes>
      </Router>
    );
  }
}

export default App;
