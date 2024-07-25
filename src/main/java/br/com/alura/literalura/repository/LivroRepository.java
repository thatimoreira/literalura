    package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Livro;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {

//    @EntityGraph(attributePaths = {"autores"})
//    List<Livro> findAll();

    @EntityGraph(attributePaths = {"autores"})
    Optional <Livro> findTopByOrderByIdDesc();

    @EntityGraph(attributePaths = {"autores"})
    List<Livro> findByIdiomasContaining(String idiomas);

    @EntityGraph(attributePaths = {"autores"})
    List<Livro> findByTituloContainingIgnoreCase(String titulo);

//    @Query("SELECT l FROM Livro l LEFT JOIN FETCH l.autores")
//    List<Livro> findAllByAutores();

//    @Transactional
//    @Modifying
//    @Query("INSERT INTO Autor (nome, anoDeNascimento, anoDeObito)" +
//            "VALUES (:nome, :anoDeNascimento, :anoDeObito)")
//    void saveAutor(@Param("nome") String nome,
//                   @Param("anoDeNascimento") int anoDeNascimento,
//                    @Param("anoDeObito") Integer anoDeObito);
}
