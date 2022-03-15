package dev.skyleaworlder.githelperplus

import org.eclipse.jgit.lib.Repository
import org.eclipse.jgit.lib.RepositoryBuilder
import java.io.File

object PrepareUtil {
    fun makeRepository(path: String): Repository {
        return RepositoryBuilder()
            .setGitDir(File(path))
            .readEnvironment()
            .findGitDir()
            .build()
    }
}