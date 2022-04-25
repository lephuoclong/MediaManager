/** @format */

import { BREAKPOINTS } from "./ComponentStyles";
import SVGFiles from "./SVGRegister";

export const SGV_REGISTER = {
  icons: {
    "not-found-image": SVGFiles.notFound,
    "plus-svg": SVGFiles.plus,
    "folder-svg": SVGFiles.folder,
    "more-svg": SVGFiles.more,
  },
};

export const BREAKPOINTS_RESPONSIVE = { ...BREAKPOINTS };
