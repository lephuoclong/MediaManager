/** @format */

import { Stack } from "@fluentui/react";
import React from "react";
import { Route, Routes } from "react-router-dom";
import Contents from "../features/contents";
import DashBoard from "../features/dashboard";
import DataRoom from "../features/dataroom";
import AppLayout from "./AppLayout";
import Header from "./components/Header";

export default function LayoutPage() {
  return (
    <Stack>
      <Header />
      <AppLayout>
        <Routes>
          {/* <Route exact path='/' element={<Contents />} /> */}
          <Route path='/contents' element={<Contents />} />
          <Route path='/data-room' element={<DataRoom />} />
          <Route path='/dashboard' element={<DashBoard />} />
          <Route path='*' element={<Contents />} />
        </Routes>
      </AppLayout>
    </Stack>
  );
}
