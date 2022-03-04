/** @format */

import { Nav, Stack } from "@fluentui/react";
import React from "react";
import { Route, Routes } from "react-router-dom";
import { LIGHT_THEME, PAGE_PATHS } from "../../constants";
import Favorites from "./Favorites";
import Garbage from "./Garbage";
import MyFolder from "./MyFolder";
import ShareWithMe from "./ShareWithMe";

const navStyles = {
  root: {
    width: 300,
    height: `100%`,
    borderRight: `2px solid ${LIGHT_THEME.palette.themeDark}`,
  },
};

const contentArea = {
  root: {
    width: "calc(100% - 300px)",
    height: "100%",
  },
};

const navGroups = [
  {
    key: "my-folder",
    name: "My Folder",
    url: `/${PAGE_PATHS.MY_FOLDER}`,
    icon: "FabricUserFolder",
  },
  {
    key: "share-with-me",
    name: "Share with me",
    url: `/${PAGE_PATHS.SHARE_WITH_ME}`,
    icon: "Share",
  },
  {
    key: "favorites",
    name: "Favorites",
    url: `/${PAGE_PATHS.FAVORITES}`,
    icon: "FavoriteStar",
  },
  {
    key: "garbage",
    name: "Garbage",
    url: `/${PAGE_PATHS.GARBAGE}`,
    icon: "RecycleBin",
  },
];

export default function Contents() {
  return (
    <Stack horizontal styles={{ root: { height: "100%" } }}>
      <Nav styles={navStyles} groups={[{ links: navGroups }]} />
      <Stack styles={contentArea}>
        <Routes>
          <Route exact path='/' element={<MyFolder />} />
          <Route path='/my-folder' element={<MyFolder />} />
          <Route path='/share-with-me' element={<ShareWithMe />} />
          <Route path='/favorites' element={<Favorites />} />
          <Route path='/garbage' element={<Garbage />} />
        </Routes>
      </Stack>
    </Stack>
  );
}
