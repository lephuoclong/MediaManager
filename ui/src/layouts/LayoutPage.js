/** @format */

import { Stack } from "@fluentui/react";
import React from "react";
import { Route, Routes } from "react-router-dom";
import Contents from "../features/contents";
import DashBoard from "../features/dashboard";
import DataRoom from "../features/dataroom";
import Profile from "../features/profile";
import Search from "../features/search";
import Setting from "../features/settings";
import CheckIn from "../security/CheckIn";
import AppLayout from "./AppLayout";
import Header from "./components/Header";

export default function LayoutPage() {
  return (
    <Stack>
      <CheckIn>
        <Stack styles={{ root: { width: "100vw", height: "100vh" } }}>
          <Header />
          <AppLayout>
            <Routes>
              <Route path='/profile' element={<Profile />} />
              <Route path='/search' element={<Search />} />
              <Route path='/setting' element={<Setting />} />
              <Route path='/data-room' element={<DataRoom />} />
              <Route path='/dashboard' element={<DashBoard />} />
              <Route path='/contents' element={<Contents />} />
              <Route path='*' element={<Contents />} />
            </Routes>
          </AppLayout>
        </Stack>
      </CheckIn>
    </Stack>
  );
}
