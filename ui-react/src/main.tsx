import React from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import App from "./App";
import { Crews } from "./crews/Crews";
import { Home } from "./Home";
import { Treasures } from "./treasures/Treasures";

ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<App />}>
          <Route index element={<Home />} />
          <Route path="treasures" element={<Treasures />} />
          <Route path="crews" element={<Crews />} />
        </Route>
      </Routes>
    </BrowserRouter>
  </React.StrictMode>
);
