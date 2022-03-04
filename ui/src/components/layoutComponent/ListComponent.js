/** @format */

import { Breadcrumb, DetailsList, Stack } from "@fluentui/react";
import React, { useEffect, useState } from "react";
import { WH_100 } from "../../constants/styles";

const wrapContent = {
  root: {
    ...WH_100.root,
    display: "flex",
  },
};

const contentFolderStyles = {
  root: {
    overflowY: "auto",
    overflowX: "hidden",
    height: "calc(100vh - 194px)",
  },
};

const items = [
  {
    text: "My Folder",
    key: "Files",
    href: "/content/files",
  },
  {
    text: "This is folder 1",
    key: "f1",
    href: "/content/f1",
  },
  {
    text: "This is folder 2",
    key: "f2",
    href: "/content/f2",
  },
  {
    text: "This is folder 3",
    key: "f3",
    href: "/content/f3",
  },
  { text: "This is folder 4", key: "f4", href: "/content/f4" },
  {
    text: "This is folder 5",
    key: "f5",
    href: "/content/f5",
    isCurrentItem: true,
  },
];

export default function ListComponent() {
  const [listFile, setListFile] = useState([]);

  useEffect(() => {
    setListFile(_getItem());
  }, []);

  const randomName = (length = 8) => {
    let chars =
      "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    let str = "";
    for (let i = 0; i < length; i++) {
      str += chars.charAt(Math.floor(Math.random() * chars.length));
    }
    return str;
  };

  const randomType = type => {
    const types = [
      "folder",
      "doc",
      "pdf",
      "ppt",
      "excel",
      "png",
      "jpg",
      "mp3",
      "mp4",
    ];
    const icon = [
      "OpenFolderHorizontal",
      "WordDocument",
      "PDF",
      "ExcelDocument",
      "PowerPointDocument",
      "PictureFill",
      "PictureCenter",
      "MusicInCollectionFill",
      "MSNVideos",
    ];
    const getIndex =
      parseInt(type, 10) === 1 ? 0 : Math.floor(Math.random() * types.length);
    return {
      fileType: types[getIndex],
      iconName: icon[getIndex],
    };
  };

  const randomDate = (begin, end) => {
    const date = new Date(
      begin.getTime() + Math.random() * (end.getTime() - begin.getTime())
    );
    return {
      value: date.valueOf(),
      dateFormatted: date.toLocaleDateString(),
    };
  };

  const randomFileSize = () => {
    const fileSize = Math.floor(Math.random() * 100) + 30;
    return {
      value: `${fileSize} KB`,
      rawSize: fileSize,
    };
  };

  const _getItem = () => {
    const items = [];
    for (let i = 0; i < 500; i++) {
      if (i < 10) {
        items.push({
          fileName: randomName(Math.floor(Math.random() * 10) + 20),
          fileCreate: randomDate(new Date(2012, 0, 1), new Date()),
          fileAuthor: randomName(Math.floor(Math.random() * 5) + 10),
          fileSize: "",
          ...randomType(1),
        });
      } else {
        items.push({
          fileName: randomName(Math.floor(Math.random() * 10) + 20),
          fileCreate: randomDate(new Date(2012, 0, 1), new Date()),
          fileAuthor: randomName(Math.floor(Math.random() * 5) + 10),
          fileSize: randomFileSize(),
          ...randomType(),
        });
      }
    }
    return items;
  };

  return (
    <Stack styles={wrapContent}>
      <Breadcrumb items={items} maxDisplayedItems={5} overflowIndex={1} />
      <Stack grow={1} styles={contentFolderStyles}>
        <DetailsList items={listFile} />
      </Stack>
    </Stack>
  );
}
