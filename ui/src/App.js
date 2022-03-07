/** @format */

import "./GlobalStyles/GlobalStyle.css";
import { Component } from "react";
import { initializeIcons } from "@fluentui/react";

import { registerIcons } from "@fluentui/react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import ErrorPage from "./features/ErrorPage";
import LayoutPage from "./layouts/LayoutPage";
import { initializeFileTypeIcons } from "@uifabric/file-type-icons";
import { SGV_REGISTER } from "./constants/SVGTheme";

class App extends Component {
  constructor(props) {
    super(props);
    initializeIcons();
    initializeFileTypeIcons();
    registerIcons(SGV_REGISTER);
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
