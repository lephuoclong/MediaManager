/** @format */

import { BREAKPOINTS } from "./ComponentStyles";
import SVGFiles from "./SVGRegister";

export const SGV_REGISTER = {
  icons: {
    "not-found-image": SVGFiles.notFound,
    "plus-svg": SVGFiles.plus,
    "folder-svg": SVGFiles.folder,
    "more-svg": SVGFiles.more,
    "upload-file-svg": SVGFiles.uploadFile,
    "doc-svg": SVGFiles.doc,
    "close-svg": SVGFiles.close,
  },
};

export const BREAKPOINTS_RESPONSIVE = { ...BREAKPOINTS };
