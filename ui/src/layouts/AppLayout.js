/** @format */

import React from "react";
import { Stack } from "@fluentui/react";
import Header from "./components/Header";

export default function AppLayout() {
  return (
    <Stack>
      <Header />
      <Stack>body</Stack>
      <Stack>footer</Stack>
    </Stack>
  );
}
