/** @format */

import React, { useState } from "react";
import PropTypes from "prop-types";
import {
  Stack,
  mergeStyleSets,
  TextField,
  Toggle,
  IconButton,
  Icon,
  Text,
} from "@fluentui/react";
import CustomButton from "../../../components/CustomButton";
import DirectoryApi from "../../../API/ModuleAPI/DirectoryApi";
import CustomModal from "../../../components/modals";
import { MODEL_NAME, TYPE_FILE } from "../../../constants";

const classNames = mergeStyleSets({
  fileInput: {
    position: "absolute",
    zIndex: 5,
    top: 0,
    bottom: 0,
    opacity: 0,
    width: "100%",
    height: "100%",
    margin: 0,
    cursor: "pointer",
  },
});

const textFieldStyles = {
  field: {
    fontSize: 12,
    lineHeight: 14,
  },
  fieldGroup: {
    height: 40,
    padding: 0,
  },
};

const btnUploadFile = {
  root: {
    position: "relative",
    width: "100%",
    height: 100,
  },
};

const btnSelectFile = {
  root: {
    width: "100%",
    height: "100%",
  },
  icon: {
    height: "100%",
  },
};

const iconFilesStyle = {
  root: {
    width: 36,
    height: 36,
  },
  height: "100%",
};

const listFileStyles = {
  root: {
    marginTop: 10,
    marginBottom: 10,
  },
};

const fileNameStyle = {
  root: {
    width: "100%",
    maxWidth: 300,
    paddingLeft: 10,
    lineHeight: 35,
  },
};

export default function AddContentSchema(props) {
  const [options, setOption] = useState(true);
  const { directoryId, onDismiss, fileType, refreshDirectory } = props;
  const [folderName, setFolderName] = useState("");
  const [filesUpload, setFilesUpload] = useState([]);
  const [modalName, setModalName] = useState("");

  const _submitContent = async () => {
    const data = {
      parentId: directoryId,
      name: folderName || "NewFolder",
      level: fileType,
    };

    const createFolder = await DirectoryApi.createFolder(data);

    if (createFolder.isAxiosError) {
      window.alert(createFolder.response.data.message, {
        title: `Error Create Directory!`,
      });
    } else {
      refreshDirectory();
      _handelDismiss();
    }
  };

  const _handelDismiss = () => {
    setFilesUpload([]);
    onDismiss();
  };

  const _selectFolderOrFile = value => {
    setOption(value);
  };

  const renderNameType = () => {
    if (Number(fileType) === 1) {
      return "Document";
    }
    if (Number(fileType) === 2) {
      return "Music";
    }
    if (Number(fileType) === 3) {
      return "Photo";
    }
    if (Number(fileType) === 4) {
      return "Movie";
    }
    return undefined;
  };

  const _handleFolderNameChange = value => {
    setFolderName(value);
  };

  const _toggleModalListFiles = (modalName = "") => {
    setFolderName([]);
    setModalName(modalName);
  };

  const _onChangeFile = value => {
    const objFiles = value.target.files;
    if (objFiles) {
      if (objFiles?.length < 10) {
        let count = 0;
        for (let index = 0; index < objFiles.length; index++) {
          if (objFiles[index].size > 5242880) {
            count++;
          }
        }

        if (count === 0) {
          let arr = [];
          Array.from(value.target.files).map((file, key) => {
            arr.push({
              name: file.name,
              url: URL.createObjectURL(file),
              id: key,
              type: file.type.replace("application/", ""),
              deleted: false,
            });
            return 1;
          });

          console.log("list file ", arr);
          setFilesUpload(arr);
          // this.setState({
          //   selectSupImgUpload: arr2,
          //   supImageUrls: arr,
          //   supImageUpload: value.target.files,
          // });
          // let supArr = this.state.supImage;
          // supArr.map(sup => {
          //   sup.deleted = 1;
          //   return 1;
          // });
          // this.setState({ supImage: supArr });
        } else {
          this.showUpdateNotify(false, "Hình upload phải nhỏ hơn 5MB");
        }
      } else {
        this.showUpdateNotify(false, "Hình ảnh phụ phải ít hơn 10 hình");
      }
    }

    _toggleModalListFiles(MODEL_NAME.LIST_FILES);
  };

  const _removeThisItem = item => {
    if (filesUpload.length === 1) {
      _toggleModalListFiles();
    }
    setFilesUpload(filesUpload.filter(i => i !== item));
  };

  const _submitFile = () => {
    console.log("list ", filesUpload);
  };

  return (
    <Stack>
      <Stack styles={{ root: { paddingBottom: 20 } }}>
        <Toggle
          label='Select folder or file'
          defaultChecked
          onText='Create Folder'
          offText={`Upload ${renderNameType()} File`}
          onChange={(_e, value) => _selectFolderOrFile(value)}
        />
        {options ? (
          <TextField
            autoFocus
            value={folderName}
            onChange={(_e, value) => _handleFolderNameChange(value)}
            styles={textFieldStyles}
          />
        ) : (
          <Stack styles={btnUploadFile}>
            <input
              label='upload file'
              name='upload-file-input'
              id='upload-file-input'
              multiple
              type='file'
              className={classNames.fileInput}
              onChange={_onChangeFile}
            />
            <IconButton
              primary
              iconProps={{ iconName: "upload-file-svg" }}
              styles={btnSelectFile}
            />
          </Stack>
        )}
      </Stack>
      <Stack horizontal horizontalAlign='space-between'>
        <CustomButton text='Cancel' onClick={_handelDismiss} />
        <CustomButton primary text='Add Content' onClick={_submitContent} />
      </Stack>
      <CustomModal
        title='List File'
        isOpen={modalName === MODEL_NAME.LIST_FILES}
        onDismiss={_toggleModalListFiles}
        primaryButtonText='Upload File'
        onPrimaryButtonClick={_submitFile}
        primaryButtonProps>
        {filesUpload &&
          filesUpload.map((file, key) => {
            return (
              <Stack
                horizontal
                horizontalAlign='space-between'
                key={key}
                styles={listFileStyles}>
                <Icon
                  iconName={
                    TYPE_FILE.filter(i => i.type === file.type)[0].iconName
                  }
                  styles={iconFilesStyle}
                />
                <Text variant='xLarge' nowrap styles={fileNameStyle}>
                  {file.name}
                </Text>

                <IconButton
                  iconProps={{
                    iconName: "close-svg",
                    styles: { icon: { height: "100%" } },
                  }}
                  onClick={() => _removeThisItem(file)}
                />
              </Stack>
            );
          })}
      </CustomModal>
    </Stack>
  );
}
AddContentSchema.propTypes = {
  directoryId: PropTypes.string,
  onDismiss: PropTypes.func.isRequired,
  fileType: PropTypes.number.isRequired,
  refreshDirectory: PropTypes.func.isRequired,
};
AddContentSchema.defaultProps = {
  directoryId: undefined,
};
