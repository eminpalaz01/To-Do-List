import React, { useState } from "react";
import { useDropzone } from "react-dropzone";
import ImageIcon from "@mui/icons-material/Image";
import AttachFileIcon from "@mui/icons-material/AttachFile";
import DownloadIcon from "@mui/icons-material/CloudDownload";
import CloudUploadIcon from "@mui/icons-material/CloudUpload";
import { Typography, Paper, Modal, List, ListItem, ListItemText, ListItemIcon, Button, IconButton } from "@mui/material";

const FileUpload = ({ onFileUpload }) => {
  const [uploadedFiles, setUploadedFiles] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);

  const onDrop = (acceptedFiles) => {
    setUploadedFiles(acceptedFiles);
    onFileUpload(acceptedFiles);
  };

  const { getRootProps, getInputProps } = useDropzone({
    onDrop,
    accept: "image/*, application/pdf, text/*",
  });

  const handleRemoveFile = (index) => {
    const updatedFiles = [...uploadedFiles];
    updatedFiles.splice(index, 1);
    setUploadedFiles(updatedFiles);
  };

  const toggleModal = () => {
    setIsModalOpen(!isModalOpen);
  };

  const handleFileInputClick = (event) => {
    event.stopPropagation(); // Tıklama olayının yayılmasını engelle
  };

  return (
    <Paper elevation={3} style={{ padding: "20px" }}>
      <div {...getRootProps()} className="dropzone"  onClick={handleFileInputClick}>
        <input {...getInputProps()} />
        <Typography variant="body1" component="p">
          Dosyaları buraya sürükleyip bırakın veya tıklayın. (txt, pdf, image)
        </Typography>
      </div>
      <div>
        <Button onClick={toggleModal}>
          <CloudUploadIcon style={{ marginRight: "8px" }} /> Dosyalar ({uploadedFiles.length})
        </Button>
        <Modal open={isModalOpen} onClose={toggleModal} style={{ display: "flex", alignItems: "center", justifyContent: "center" }}>
          <div style={{ width: "500px", maxHeight: "500px", overflowY: "auto", padding: "20px", backgroundColor: "white" }}>
            <h4>Yüklenen Dosyalar:</h4>
            <List>
              {uploadedFiles.map((file, index) => (
                <ListItem key={index}>
                  <ListItemIcon>
                    {file.type.startsWith("image") ? <ImageIcon /> : <AttachFileIcon />}
                  </ListItemIcon>
                  <ListItemText primary={file.name} />
                  <IconButton onClick={() => handleRemoveFile(index)}>
                    -
                  </IconButton>
                  <IconButton href={URL.createObjectURL(file)} download={file.name}>
                    <DownloadIcon style={{ fontSize: "1rem" }} />
                  </IconButton>
                </ListItem>
              ))}
            </List>
          </div>
        </Modal>
      </div>
    </Paper>
  );
};

export default FileUpload;