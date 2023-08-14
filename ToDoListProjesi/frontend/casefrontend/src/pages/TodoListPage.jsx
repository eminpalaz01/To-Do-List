import React, { useEffect, useState } from "react";
import {
  Container,
  Paper,
  Typography,
  TextField,
  Chip,
  Button,
  Modal,
  Backdrop,
  Fade,
  Autocomplete,
  Box,
} from "@mui/material";
import ExitToAppOutlinedIcon from "@mui/icons-material/ExitToAppOutlined";
import "../css/TodoListCss.css";
import axiosBackendApi from "../AxiosBackend";
import FileUpload from "../components/FileUpload";
import SingleImageUpload from "../components/SingleImageUpload";

const TodoList = ({ isLoggedIn }) => {
  const [error, setError] = useState(null);
  const [tasks, setTasks] = useState([]);
  const [searchText, setSearchText] = useState("");
  const [selectedTag, setSelectedTag] = useState(null);
  const [selectedTask, setSelectedTask] = useState(null);
  const [open, setOpen] = useState(false);
  const [tagOptions, setTagOptions] = useState([]);
  const [newTask, setNewTask] = useState({
    title: "",
    description: "",
    tags: [],
  }); // Yeni görev ekleme durumu
  const [isAddingTask, setIsAddingTask] = useState(false);
  const [isEditingTask, setIsEditingTask] = useState(false);

  useEffect(() => {
    fetchTodoList();
    fetchTagList();
  }, []);

  const handleFileUpload = () => {
    // Dosya yükleme işlemleri
  };

  const handleImageSelect = (image) => {
    if (image) {
      // Seçilen resmi işle
      console.log("Seçilen resim:", image);
    } else {
      // Resim kaldırıldı
      console.log("Resim kaldırıldı");
    }
  };

  const handleLogout = () => {
    localStorage.clear();
    window.location.reload();
  };

  const fetchTagList = async () => {
    try {
      let url = "/tag";

      const response = await axiosBackendApi.get(url);

      let tagList = [];
      response.data.data.forEach((tag) => {
        tagList.push(tag.name);
      });

      setTagOptions(tagList);
    } catch (error) {
      if (error.response && error.response.status === 401) {
        setError("Unauthorized"); // Hata durumunu işaretle
      } else {
        console.log(error.message);
      }
      console.error("Error fetching data:", error);
    }
  };

  const fetchTodoList = async () => {
    try {
      let url = "/todo/";
      let urlWithUsername = url.concat(localStorage.getItem("username"));

      const response = await axiosBackendApi.get(urlWithUsername);

      let todos = [];
      response.data.data.forEach((todo) => {
        let todoTags = [];

        todo.tags.forEach((tag) => {
          todoTags.push(tag.name);
        });

        let currentTodo = {
          id: todo.id,
          title: todo.title,
          description: todo.description,
          tags: todoTags,
        };
        todos.push(currentTodo);
      });
      setTasks(todos);
    } catch (error) {
      if (error.response && error.response.status === 401) {
        setError("Unauthorized"); // Hata durumunu işaretle
        setTimeout(() => {
          window.location.reload(); // Sayfayı yenile
        }, 1000); // İsteğe bağlı olarak bekletme süresi
      } else {
        console.log(error.message);
      }
      console.error("Error fetching data:", error);
    }
  };

  const handleAddTask = async () => {
    if (!newTask.title) {
      return; // Başlık boşsa ekleme yapma
    }

    let url = "/todo";
    try {
      let response = await axiosBackendApi.post(url, {
        title: newTask.title,
        description: newTask.description,
        personName: localStorage.getItem("username"),
        tags: newTask.tags
      });

      const updatedTasks = [...tasks, response.data.data];
      setTasks(updatedTasks);

      // Modalı kapatın ve yeni görev durumunu sıfırlayın
      setIsAddingTask(false);
      setNewTask({ title: "", description: "", tags: [] });
    } catch (error) {
      if (error.response && error.response.status === 401) {
        setError("Unauthorized"); // Hata durumunu işaretle
        setTimeout(() => {
          window.location.reload(); // Sayfayı yenile
        }, 1000); // İsteğe bağlı olarak bekletme süresi
      } else {
        console.log(error.message);
      }
    }
  };

  const handleDeleteTask = async (id) => {
    let baseUrl = "/todo/";
    let url = baseUrl.concat(id);
    try {
      const response = await axiosBackendApi.delete(url);

      if (response.data.success) {
        const newTasks = tasks.filter((task) => task.id !== id);
        setTasks(newTasks);
      }
    } catch (error) {
      if (error.response && error.response.status === 401) {
        setError("Unauthorized"); // Hata durumunu işaretle
        setTimeout(() => {
          window.location.reload(); // Sayfayı yenile
        }, 1000); // İsteğe bağlı olarak bekletme süresi
      } else {
        console.log(error.message);
      }
    }
  };

  const handleEditTask = (task) => {
    setSelectedTask(task);
    setIsEditingTask(true);
  };

  const handleSaveEdit = async () => {
    if (!selectedTask?.title) {
      return; // Başlık boşsa kaydetme yapma
    }

    let url = "/todo";

    try {
      let response = await axiosBackendApi.post(url, {
        id: selectedTask.id,
        title: selectedTask.title,
        description: selectedTask.description,
        personName: localStorage.getItem("username"),
        tags: selectedTask.tags,
      });

      // Güncellenmiş görev listesini oluşturun
      const updatedTasks = tasks.map((task) =>
        task.id === selectedTask.id ? response.data.data : task
      );

      // Güncellenmiş görev listesini ayarlayın ve düzenleme durumunu sıfırlayın
      setTasks(updatedTasks);
      setSelectedTask(null);
      setIsEditingTask(false);
    } catch (error) {
      if (error.response && error.response.status === 401) {
        setError("Unauthorized"); // Hata durumunu işaretle
        setTimeout(() => {
          window.location.reload(); // Sayfayı yenile
        }, 1000); // İsteğe bağlı olarak bekletme süresi
      } else {
        console.log(error.message);
      }
    }
  };

  const handleSearch = async () => {
    // Arama işlemleri
    if (selectedTag === "") {
      setSelectedTag(null);
    }

    try {
      let url = "/todo/";
      let urlWithUsername = url.concat(localStorage.getItem("username"));

      const response = await axiosBackendApi.post(urlWithUsername, {
        tagName: selectedTag,
        title: searchText,
      });

      let todos = [];
      response.data.data.forEach((todo) => {
        let todoTags = [];

        todo.tags.forEach((tag) => {
          todoTags.push(tag.name);
        });

        let currentTodo = {
          id: todo.id,
          title: todo.title,
          description: todo.description,
          tags: todoTags,
        };
        todos.push(currentTodo);
      });
      setTasks(todos);
    } catch (error) {
      if (error.response && error.response.status === 401) {
        setError("Unauthorized"); // Hata durumunu işaretle
        setTimeout(() => {
          window.location.reload(); // Sayfayı yenile
        }, 1000); // İsteğe bağlı olarak bekletme süresi
      } else {
        console.log(error.message);
      }
    }
  };

  return (
    <div className="app">
      <Container maxWidth="md" className="todo-list-container">
        {isLoggedIn ? (
          <Paper elevation={3} className="todo-list-paper">
            <Typography variant="h5" style={{ textAlign: "center" }}>
              To-Do List
            </Typography>
            <div className="search-container">
              <TextField
                label="Başlık Ara"
                value={searchText}
                onChange={(e) => setSearchText(e.target.value)}
                fullWidth
                margin="normal"
                style={{ maxHeight: "50px" }}
              />
              <Autocomplete
                style={{ marginTop: 10 }}
                id="tags-outlined"
                options={tagOptions} // Mevcut etiketleri buraya ekleyin
                value={selectedTag}
                onChange={(event, newValue) => setSelectedTag(newValue)}
                renderInput={(params) => (
                  <TextField
                    {...params}
                    label="Etiket Seç"
                    variant="outlined"
                  />
                )}
              />
              <div
                style={{
                  display: "flex",
                  flexDirection: "flex",
                  justifyContent: "center",
                  gap: "10px",
                  marginTop: 10,
                }}
              >
                <Button
                  onClick={handleSearch}
                  variant="contained"
                  color="primary"
                >
                  Ara
                </Button>
                <Button
                  onClick={() => setIsAddingTask(true)}
                  variant="contained"
                  color="primary"
                >
                  Yeni Görev Ekle
                </Button>
              </div>
            </div>
            <ul style={{ listStyle: "none", padding: 0 }}>
              {tasks.map((task) => (
                <li
                  key={task.id}
                  style={{ marginTop: 30 }}
                  className="task-item"
                >
                  <div className="task-details">
                    <div className="chip-container">
                      {task.tags.map((tag) => (
                        <Chip
                          style={{ backgroundColor: "#bebebe", marginRight: 3 }}
                          key={tag}
                          size="small"
                          label={"#" + tag}
                          className="chip"
                        />
                      ))}
                    </div>
                    <Typography variant="h6">{task.title}</Typography>
                  </div>
                  <div className="task-buttons">
                    <Button
                      style={{ marginRight: 5 }}
                      variant="outlined"
                      color="primary"
                      onClick={() => handleEditTask(task)}
                    >
                      Düzenle
                    </Button>
                    <Button
                      variant="outlined"
                      color="secondary"
                      onClick={() => handleDeleteTask(task.id)}
                    >
                      Sil
                    </Button>
                  </div>
                </li>
              ))}
            </ul>
            <Modal
              open={isAddingTask}
              onClose={() => {
                setIsAddingTask(false);
                setNewTask({ title: "", description: "", tags: [] });
              }}
            >
              <Fade in={isAddingTask}>
                <div className="modal">
                  <div
                    className="modal-content"
                    style={{ border: "2px solid #ccc", padding: "20px" }}
                  >
                    <Typography variant="h6">Ekle</Typography>
                    <TextField
                      label="Başlık"
                      value={newTask.title}
                      onChange={(e) =>
                        setNewTask({ ...newTask, title: e.target.value })
                      }
                      fullWidth
                      margin="normal"
                    />
                    <TextField
                      label="Açıklama"
                      value={newTask.description}
                      onChange={(e) =>
                        setNewTask({ ...newTask, description: e.target.value })
                      }
                      fullWidth
                      margin="normal"
                    />
                    <Autocomplete
                      multiple
                      id="tags-outlined"
                      options={tagOptions} // Mevcut etiketlerinizi ekleyin
                      value={newTask.tags}
                      onChange={(event, newValue) =>
                        setNewTask({ ...newTask, tags: newValue })
                      }
                      renderInput={(params) => (
                        <TextField
                          {...params}
                          label="Etiketler"
                          variant="outlined"
                        />
                      )}
                    />
                    <div>
                      <h5>Resim yükle</h5>
                      <SingleImageUpload onImageSelect={handleImageSelect} />
                    </div>
                    <div>
                      <FileUpload onFileUpload={handleFileUpload} />
                    </div>
                    <div className="modal-buttons">
                      <Button
                        onClick={handleAddTask}
                        variant="contained"
                        color="primary"
                      >
                        Ekle
                      </Button>
                      <Button
                        onClick={() => setIsAddingTask(false)}
                        variant="contained"
                        color="primary"
                      >
                        İptal
                      </Button>
                    </div>
                  </div>
                </div>
              </Fade>
            </Modal>
            <Modal
              open={isEditingTask}
              onClose={() => {
                setIsEditingTask(false);
                setSelectedTask(null); // Düzenleme modalı kapanırken seçili görevi sıfırla
              }}
              closeAfterTransition
              BackdropComponent={Backdrop}
              BackdropProps={{
                timeout: 500,
                style: { backgroundColor: "rgba(0, 0, 0, 0)" },
              }}
            >
              <Fade in={isEditingTask}>
                <div className="modal">
                  <div
                    className="modal-content"
                    style={{ border: "2px solid #ccc", padding: "20px" }}
                  >
                    <Typography variant="h6">Düzenle</Typography>
                    <TextField
                      label="Başlık"
                      value={selectedTask?.title || ""}
                      onChange={(e) =>
                        setSelectedTask({
                          ...selectedTask,
                          title: e.target.value,
                        })
                      }
                      fullWidth
                      margin="normal"
                    />
                    <TextField
                      label="Açıklama"
                      value={selectedTask?.description || ""}
                      onChange={(e) =>
                        setSelectedTask({
                          ...selectedTask,
                          description: e.target.value,
                        })
                      }
                      fullWidth
                      margin="normal"
                    />
                    <Autocomplete
                      multiple
                      id="tags-outlined"
                      options={tagOptions} // Mevcut etiketlerinizi ekleyin
                      value={selectedTask?.tags || []}
                      onChange={(event, newValue) =>
                        setSelectedTask({ ...selectedTask, tags: newValue })
                      }
                      renderInput={(params) => (
                        <TextField
                          {...params}
                          label="Etiketler"
                          variant="outlined"
                        />
                      )}
                    />
                    <div>
                      <h5>Resim yükle</h5>
                      <SingleImageUpload onImageSelect={handleImageSelect} />
                    </div>
                    <div>                     
                      <FileUpload onFileUpload={handleFileUpload} />
                    </div>
                    <div className="modal-buttons">
                      <Button
                        onClick={handleSaveEdit}
                        variant="contained"
                        color="primary"
                      >
                        Kaydet
                      </Button>
                      <Button
                        onClick={() => setIsEditingTask(false)} // Buradaki setOpen'i setIsEditingTask olarak değiştirin
                        variant="contained"
                        color="primary"
                      >
                        İptal
                      </Button>
                    </div>
                  </div>
                </div>
              </Fade>
            </Modal>
          </Paper>
        ) : (
          <Typography variant="h6">Lütfen giriş yapın.</Typography>
        )}
        <Box position="fixed" bottom={16} right={16}>
          <Button
            startIcon={<ExitToAppOutlinedIcon style={{ marginLeft: "10px" }} />}
            variant="contained"
            color="secondary"
            onClick={handleLogout}
          ></Button>
        </Box>
      </Container>
    </div>
  );
};

export default TodoList;
